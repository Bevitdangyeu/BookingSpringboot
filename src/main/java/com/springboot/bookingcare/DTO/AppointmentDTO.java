package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppointmentDTO {
    private int appointmentId;
    private String fullName;
    private int sex;
    private String phoneNumber;
    private String address;
    private String description;
    private String status;
    private String image;
    private String dateOfBirth;
    private LocalDate createAt;
    private DoctorDTO doctor;
    private TimeDTO time;
}
