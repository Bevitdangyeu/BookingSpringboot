package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Service.AccountService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin1")
public class Account {
    @Autowired
    AccountService accountService;
    @GetMapping("/getAll/user")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> listUser=accountService.getAllUser();
        if (listUser == null || listUser.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về HTTP 204 No Content
        }

        return ResponseEntity.ok(listUser); // Trả về HTTP 200 và danh sách user
    }
    @GetMapping("/getAll/doctor")
    public ResponseEntity<List<DoctorDTO>> getAllDoctor(){
        List<DoctorDTO> listDoctor=accountService.getAllDoctor();
        if(listDoctor==null||listDoctor.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listDoctor);
    }
    @PostMapping("/lock/{id}")
    public ResponseEntity<UserDTO> lockUser(@PathVariable("id") int id){
        UserDTO userDTO=accountService.lockUser(id);
        if(userDTO!=null){
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
