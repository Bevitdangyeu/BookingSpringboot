package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/doctor")
public class DoctorAPI {
    @Autowired
    DoctorService doctorService;
    @GetMapping("/list")
    public List<DoctorDTO> GetList(){
       return doctorService.findAllDoctor();
   }
    @GetMapping("/profile/{id}")
    public DoctorDTO getProfile(@PathVariable("id") int id){
        return doctorService.findByDoctorId(id);
    }
}
