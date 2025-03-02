package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int idUser;
    private String fullName;
    private String email;
    private String image;
    private String role;
    private String fileName;
    private String username;
    private String password;
    private String coverPhoto;
    private String address;

}
