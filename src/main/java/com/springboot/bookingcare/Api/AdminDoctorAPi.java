package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.Service.DoctorService;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminDoctorAPi {
    @Autowired
    DoctorService doctorService;
    @GetMapping("/doctor/profile")
    public DoctorDTO findDoctorProfile(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails user=(UserDetails) authentication.getPrincipal();
            // ép user về customUserDetail
            CustomeUserDetails custome=(CustomeUserDetails) user;
            int id=custome.getUser().getIdUser();
            return doctorService.findByDoctorForEdit(id);
        }
        return null;
    }
    @PostMapping("/doctor/profile/update")
    public DoctorDTO updateProfile(@RequestBody DoctorDTO doctorDTO){
        try {
            return this.doctorService.add(doctorDTO);
        }catch (Exception e){
            System.out.println("Lỗi tại doctor: "+e.getMessage());
        }
        return null;
    }
}
