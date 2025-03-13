package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.AppointmentRequest;
import com.springboot.bookingcare.ServiceImplement.AppointmentProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class AppointmentAPI {
    private final AppointmentProducer appointmentProducer;

    public AppointmentAPI(AppointmentProducer appointmentProducer) {
        this.appointmentProducer = appointmentProducer;
    }
    @PostMapping("/appoientment/add")
    public ResponseEntity<Map<String,String>> bookAppointment(@RequestBody AppointmentRequest request) {
      Map responseFromConsumer= appointmentProducer.sendAppointmentRequest(request);
      if(responseFromConsumer.get("status").equals("200")){
          return ResponseEntity.ok(responseFromConsumer);
      }
      else{
         return ResponseEntity.status(HttpStatus.CONFLICT).body(responseFromConsumer);
      }
    }
    @PostMapping("/appoientment/addd")
    public ResponseEntity<String> bookingAppointment(@RequestBody AppointmentRequest request) {
        appointmentProducer.sendEmailRequest(request);
        return ResponseEntity.ok("Email xác nhận đã được gửi!");
    }
}
