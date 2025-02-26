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
    private String certificate;// số chứng chỉ
    private int idDoctor;
    private String expertise;
    private int experience;
    private String hospital;
    private int star;
    private String fileName;
    private String username;
    private String password;
    private String coverPhoto;
    private String description;
    private String address;
    private String phoneNumber;
}
