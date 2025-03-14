package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.Config.RabbitMQConfig;
import com.springboot.bookingcare.DTO.AppointmentRequest;
import com.springboot.bookingcare.Service.AppoinmentService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class AppointmentConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    AppoinmentService appoinmentService;

    private static final ConcurrentHashMap<String, Boolean> bookedSlots = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public Map<String, String> consumerAppointmentRequest(AppointmentRequest appointmentRequest){
        //tìm trong cơ sở dữ liệu xem lịch khám đó đã được đặt chưa
        System.out.println("Vào nơi nhận tin nhắn: " );
        int time=appointmentRequest.getTime().getTimeId();
        int doctor=appointmentRequest.getDoctor().getExperience();
        LocalDate date=appointmentRequest.getDate();
        String key = time + "-" + doctor + "-" + date.toString();
        Map<String, String> response = new HashMap<>();
        String responseMessage;
        if(bookedSlots.putIfAbsent(key,true)==null){
            // gọi service để thêm vào cơ sở dữ liệu
            boolean value=appoinmentService.addAppointment(appointmentRequest);
            scheduler.schedule(() -> {
                try {
                    System.out.println("Trước khi xóa: bookedSlots.containsKey(" + key + ") = " + bookedSlots.containsKey(key));
                    boolean removed = bookedSlots.remove(key) != null;
                    System.out.println("Slot đã bị xóa sau 5 phút: " + key + " | Thành công: " + removed);
                    System.out.println("Sau khi xóa: bookedSlots.containsKey(" + key + ") = " + bookedSlots.containsKey(key));
                } catch (Exception e) {
                    System.out.println("Lỗi khi xóa key: " + key);
                    e.printStackTrace();
                }
            }, 5, TimeUnit.MINUTES);
            if(value){
                responseMessage="Chúc mừng: đã đặt lịch thành công ";
                response.put("message",responseMessage);
                response.put("status","200");
                return response;
            }else{
                responseMessage="Đặt lịch thất bại, lỗi hệ thống";
                response.put("message",responseMessage);
                response.put("status","400");
                return response;
            }
        }
        else{
            System.out.println("Lịch hẹn thất bại (đã có người đặt): " + appointmentRequest.getFullName());
            responseMessage="Lịch hẹn thất bại (đã có người đặt): ";
            response.put("message",responseMessage);
            response.put("status","409");
            return response;
        }

    }
}
