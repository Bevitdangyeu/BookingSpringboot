package com.springboot.bookingcare.DTO;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.TimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentRequest implements Serializable  {
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
