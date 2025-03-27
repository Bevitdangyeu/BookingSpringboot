package com.springboot.bookingcare.DTO;

import com.springboot.bookingcare.Entity.AppointmentEntity;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewsDTO {
    private int reviewsId;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private UserDTO user;
    private DoctorDTO doctor;
    private AppointmentDTO appointment;
    private int start;
}
