package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DoctorDTO implements Serializable {
    private int idDoctor;
    private String certificate;// số chứng chỉ
    private String expertise;
    private int experience;
    private String hospital;
    private int star;
    private String description;
    private String phoneNumber;
    private UserDTO userDTO;
    private String shortDescription;
}
