package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.DoctorDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/doctor")
public class DoctorAPI {
   @GetMapping("/list")
   public List<DoctorDTO> GetList(){
       List<DoctorDTO> listDoctor=new ArrayList<>();
       DoctorDTO doctorDTO=new DoctorDTO();
       doctorDTO.setIdDoctor(1);
       doctorDTO.setFullName(" Xuân Mai");
       listDoctor.add(doctorDTO);
       return listDoctor;
   }
}
