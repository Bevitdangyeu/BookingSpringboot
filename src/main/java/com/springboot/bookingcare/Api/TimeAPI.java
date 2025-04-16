package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.TimeDTO;
import com.springboot.bookingcare.Service.TimeService;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
public class TimeAPI {
    @Autowired
    TimeService timeService;
    @GetMapping("/public/time/{id}/{time}")
    public List<TimeDTO> getTime(@PathVariable("id")int id,@PathVariable("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time){
        List<TimeDTO> timeDTOList=timeService.getTimeByIdDoctor(id,time);
        return timeDTOList;
    }
    @GetMapping("/public/time/findAll")
    public List<TimeDTO> getAllTime(){
        return  timeService.findAll();
    }
    @GetMapping("/doctor/time")
    public List<TimeDTO> getAllTimeByDoctor(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();
            CustomeUserDetails customeUserDetails=(CustomeUserDetails) userDetails;
            int id=customeUserDetails.getUser().getIdUser();
            return timeService.findTimeForDoctor(id);
        }
        return null;
    }
    @PostMapping("/doctor/time/update")
    public List<TimeDTO> update(@RequestBody List<TimeDTO> timeDTO){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();
            CustomeUserDetails customeUserDetails=(CustomeUserDetails) userDetails;
            int id=customeUserDetails.getUser().getIdUser();
            try{
                return timeService.updateTime(id,timeDTO);
            }catch (Exception e){
                System.out.println("Loi: "+e.getMessage());
            }
        }
        return null;
    }
}
