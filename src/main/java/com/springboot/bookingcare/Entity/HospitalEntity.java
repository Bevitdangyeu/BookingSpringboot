package com.springboot.bookingcare.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="hospital")
public class HospitalEntity {
    @Id
    @Column(name="hospitalId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    @SequenceGenerator(name = "hospital_seq", sequenceName = "hospital_hospitalId_seq", allocationSize = 1)
    private int hospitalId;
    @Column(name="nameHospital")
    private String hospitalName;
    @Column(name="address")
    private String address;
    @OneToMany(mappedBy="hospitalId")
    private List<DoctorEntity> listDoctor=new ArrayList<>();
}
