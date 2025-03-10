package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="Evaluate")
@Getter
@Setter
public class EvaluateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Evaluate_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "evaluate_evaluateid_seq", allocationSize = 1)
    private int evaluateId;
    @Column(name="content")
    private String content;
    @Column(name="time")
    private LocalDateTime time;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser")
    private UserEntity userId=new UserEntity(); // id người đánh giá=> phải ăng nhập trước
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idDocotr")
    private DoctorEntity doctorId=new DoctorEntity();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idDatLich")
    private AppointmentEntity appointment;
}

