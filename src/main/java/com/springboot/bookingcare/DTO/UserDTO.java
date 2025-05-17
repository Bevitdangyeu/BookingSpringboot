package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {
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
    private boolean active;

}
