package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.Config.RabbitMQConfig;
import com.springboot.bookingcare.DTO.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class AppointmentProducer {
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    public AppointmentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendAppointmentRequest(AppointmentRequest appointmentRequest){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"appointment_routing", appointmentRequest);
        System.out.println("Đã gửi tin nhắn: " + appointmentRequest.getFullName());
    }
    public void sendEmailRequest(AppointmentRequest appointmentRequest){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"email_routing",appointmentRequest);
    }
}
