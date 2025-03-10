package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.Config.RabbitMQConfig;
import com.springboot.bookingcare.DTO.AppointmentRequest;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
@Service
public class AppointmentConsumer {
    private static final ConcurrentHashMap<String, Boolean> bookedSlots = new ConcurrentHashMap<>();
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void consumerAppointmentRequest(AppointmentRequest appointmentRequest){
        System.out.println("Vào nơi nhận tin nhắn: " );
        String time=appointmentRequest.getTime();
        if(bookedSlots.putIfAbsent(time,true)==null){
            System.out.println("Chúc mừng: " + appointmentRequest.getFullName()+" dã đặt lịch thành công");
        }
        else{
            System.out.println("Lịch hẹn thất bại (đã có người đặt): " + appointmentRequest.getFullName());
        }
    }
}
