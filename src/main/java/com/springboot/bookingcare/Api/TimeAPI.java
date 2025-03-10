package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.TimeDTO;
import com.springboot.bookingcare.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/public")
public class TimeAPI {
    @Autowired
    TimeService timeService;
    @GetMapping("/time/{id}/{time}")
    public List<TimeDTO> getTime(@PathVariable("id")int id,@PathVariable("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time){
        List<TimeDTO> timeDTOList=timeService.getTimeByIdDoctor(id,time);
        return timeDTOList;
    }
}
