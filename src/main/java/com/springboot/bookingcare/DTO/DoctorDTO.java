package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {
    private int idDoctor;
    private String certificate;// số chứng chỉ
    private String expertise;
    private int experience;
    private String hospital;
    private int star;
    private String description;
    private String phoneNumber;
    private UserDTO userDTO;
}
