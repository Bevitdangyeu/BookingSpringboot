package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
    @Autowired
    UserService userService;
    @PostMapping("/public/add/user")
    public UserDTO AddUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

}
