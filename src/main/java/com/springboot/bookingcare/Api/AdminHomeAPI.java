package com.springboot.bookingcare.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminHomeAPI {
        @GetMapping("/admin1")
        public String HomePage(){
            System.out.println("đã va admin1");
            return " Được phép truy cập";
        }
}
