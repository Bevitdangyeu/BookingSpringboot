package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.AppointmentRequest;
import com.springboot.bookingcare.ServiceImplement.AppointmentProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class AppointmentAPI {
    private final AppointmentProducer appointmentProducer;

    public AppointmentAPI(AppointmentProducer appointmentProducer) {
        this.appointmentProducer = appointmentProducer;
    }
    @PostMapping("/appoientment/add")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequest request) {
        appointmentProducer.sendAppointmentRequest(request);
        return ResponseEntity.ok("Yêu cầu đặt lịch đã được gửi!");
    }
    @PostMapping("/appoientment/addd")
    public ResponseEntity<String> bookingAppointment(@RequestBody AppointmentRequest request) {
        appointmentProducer.sendEmailRequest(request);
        return ResponseEntity.ok("Email xác nhận đã được gửi!");
    }
}
