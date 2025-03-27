package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/doctor")
public class DoctorAPI {
    @Autowired
    DoctorService doctorService;
    @GetMapping("/list")
    public List<DoctorDTO> GetList(){
        System.out.println("Vào API để lấy danh sách bác sĩ vào lúc: "+ LocalDateTime.now());
        return doctorService.findAllDoctor();
   }
    @GetMapping("/profile/{id}")
    public DoctorDTO getProfile(@PathVariable("id") int id){
        return doctorService.findByDoctorId(id);
    }
    @PostMapping("/add")
    public DoctorDTO add(@RequestBody DoctorDTO doctor){
        return doctorService.add(doctor);
    }
    @PostMapping("/update")
    public DoctorDTO update(@RequestBody DoctorDTO doctor){
        return doctorService.add(doctor);
    }
}
