package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalDTO {
    private int hospitalId;
    private String hospitalName;
    private String address;
    private String image;
}
