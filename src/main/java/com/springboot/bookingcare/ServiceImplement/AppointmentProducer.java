package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.Config.RabbitMQConfig;
import com.springboot.bookingcare.DTO.AppointmentRequest;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AppointmentProducer {
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    public AppointmentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public Map<String,String> sendAppointmentRequest(AppointmentRequest appointmentRequest){
        // Tạo Correlation ID để track request này
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitMQConfig.EXCHANGE_NAME,
               "appointment_routing",
                appointmentRequest,
                correlationData
        );
        return ((Map<String, String>) response);
        // Ép kiểu phản hồi về đúng dạng ResponseEntity<Map<String, String>>
//        if (response instanceof ResponseEntity) {
//            return (ResponseEntity<Map<String, String>>) response;
//        } else {
//            // Trường hợp không nhận được phản hồi
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("message", "Lỗi: Không nhận được phản hồi từ Consumer!");
//            errorResponse.put("status", "500");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
    }
    public void sendEmailRequest(AppointmentRequest appointmentRequest){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"email_routing",appointmentRequest);
    }
}
