package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.Config.RabbitMQConfig;
import com.springboot.bookingcare.DTO.AppointmentRequest;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class AppointmentConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final ConcurrentHashMap<Integer, Boolean> bookedSlots = new ConcurrentHashMap<>();
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public Map<String, String> consumerAppointmentRequest(AppointmentRequest appointmentRequest){
        System.out.println("Vào nơi nhận tin nhắn: " );
        int time=appointmentRequest.getTime().getTimeId();
        Map<String, String> response = new HashMap<>();
        String responseMessage;
        if(bookedSlots.putIfAbsent(time,true)==null){
            // gọi service để thêm vào cơ sở dữ liệu
            responseMessage="Chúc mừng: đã đặt lịch thành công ";
            response.put("message",responseMessage);
            response.put("status","200");
            return response;
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
