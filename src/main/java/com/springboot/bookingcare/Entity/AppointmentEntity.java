package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name="appointment")
@Entity
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "appointment_appointmentid_seq", allocationSize = 1)
    @Column(name="appoinmentId")
    private int appoinmentId;
    @Column(name="fullName")
    private String fullName;
    @Column(name="Sex")
    private int sex;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="address")
    private String address;
    @Column(name="description")
    private String description;
    @Column(name="status")
    private String status;
    @Column(name="image")
    private String image;
    @Column(name="dateOfBirth")
    private String dateOfBirth;
    @Column(name="createAt")
    private LocalDate createAt;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="time")
    private TimeEntity time=new TimeEntity();
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idDocotr")
    private DoctorEntity doctor=new DoctorEntity();
    @OneToOne(mappedBy="appointment",fetch = FetchType.LAZY)
    private EvaluateEntity evaluate;
}
