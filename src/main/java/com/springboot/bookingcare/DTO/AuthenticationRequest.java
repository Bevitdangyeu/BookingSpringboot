package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String userName;
    private String password;
    private String token;
}
