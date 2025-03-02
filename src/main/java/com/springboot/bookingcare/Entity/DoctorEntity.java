package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="doctor")
public class DoctorEntity {
    @Id
    @Column(name="doctorId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_doctorId_seq", allocationSize = 1)
    private int doctorId;
    @Column(name="experience")
    private int experience;
    @Column(name="star")
    private int star;
    @Column (name="expertise")
    private String expertise;
    @Column(name="certificate")
    private String certificate; // giấy chứng nhận
    @Column (name="description",columnDefinition = "TEXT")
    private String description;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser")
    private UserEntity user;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idHospital")
    private HospitalEntity hospitalId;
}
