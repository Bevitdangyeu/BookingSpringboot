package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.HospitalDTO;
import com.springboot.bookingcare.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/hospital")
public class HospitalAPI {
    @Autowired
    HospitalService hospitalService;
    @GetMapping("/findAll")
    public List<HospitalDTO> findAll(){
        return hospitalService.findAll("Đồng Tháp");
    }
}
