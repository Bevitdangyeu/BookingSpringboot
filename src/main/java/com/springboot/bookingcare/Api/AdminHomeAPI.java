package com.springboot.bookingcare.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminHomeAPI {
        @GetMapping("/admin1")
        public String HomePage(){
            return " Được phép truy cập";
        }
}
