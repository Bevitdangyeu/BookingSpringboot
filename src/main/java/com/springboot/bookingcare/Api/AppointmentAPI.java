package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.AppointmentDTO;
import com.springboot.bookingcare.DTO.AppointmentRequest;
import com.springboot.bookingcare.Service.AppointmentService;
import com.springboot.bookingcare.Service.AppointmentService;
import com.springboot.bookingcare.ServiceImplement.AppointmentProducer;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentAPI {
    @Autowired
    AppointmentService appointmentService;
    private final AppointmentProducer appointmentProducer;

    public AppointmentAPI(AppointmentProducer appointmentProducer) {
        this.appointmentProducer = appointmentProducer;
    }
    @PostMapping("/public/appointment/add")
    public ResponseEntity<Map<String,String>> bookAppointment(@RequestBody AppointmentRequest request) {
      Map responseFromConsumer= appointmentProducer.sendAppointmentRequest(request);
      if(responseFromConsumer.get("status").equals("200")){
          return ResponseEntity.ok(responseFromConsumer);
      }
      else{
         return ResponseEntity.status(HttpStatus.CONFLICT).body(responseFromConsumer);
      }
    }
    @GetMapping("/user/appointment/list")
    public List<AppointmentDTO> findAllPublic(){
        // lấy thông tin user từ security contex
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails user=(UserDetails) authentication.getPrincipal();
            // ép user về customUserDetail
            CustomeUserDetails custome=(CustomeUserDetails) user;
            int id=custome.getUser().getIdUser();
            for(AppointmentDTO a: appointmentService.findAllForUser(id)){
                System.out.println(a.getAppointmentId());
            }
            return appointmentService.findAllForUser(id);
        }
        return null;
    }
    @GetMapping("/doctor/appointment/{date}")
    public List<AppointmentDTO> findAllByDoctorId(@PathVariable("date") String date)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails userDetails=(UserDetails)authentication.getPrincipal();
            CustomeUserDetails user=(CustomeUserDetails) userDetails;
            int id=user.getUser().getIdUser();
            return appointmentService.findAllByDoctorId(id,date);
        }
        return null;
    }
    @GetMapping("/doctor/appointment/statistical/{month}")
    public  List<AppointmentDTO> findAllByMonth(@PathVariable("month")String month){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails userDetails=(UserDetails)authentication.getPrincipal();
            CustomeUserDetails user=(CustomeUserDetails) userDetails;
            int id=user.getUser().getIdUser();
            return appointmentService.findByMonth(id,month);
        }
        return null;
    }
    @PostMapping("/doctor/appointment/update/{appointmentId}/{status}")
    public ResponseEntity<Map<String, Object>> updateStatus(
            @PathVariable("appointmentId") int id,
            @PathVariable("status") String status) {

        AppointmentDTO updated = appointmentService.updateStatus(id, status);
        Map<String, Object> response = new HashMap<>();

        if (updated!=null) {
            response.put("success", true);
            response.put("appointment",updated);
            return ResponseEntity.ok(response);
        }
        response.put("success", false);
        response.put("appointment",null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @PostMapping("/public/appointment/update/{appointmentId}/{status}")
    public ResponseEntity<Map<String, Object>> Cancel(
            @PathVariable("appointmentId") int id,
            @PathVariable("status") String status) {

        AppointmentDTO updated = appointmentService.updateStatus(id, status);
        Map<String, Object> response = new HashMap<>();

        if (updated!=null) {
            response.put("success", true);
            response.put("appointment",updated);
            return ResponseEntity.ok(response);
        }
        response.put("success", false);
        response.put("appointment",null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
