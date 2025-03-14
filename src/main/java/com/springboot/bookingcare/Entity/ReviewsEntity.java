package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="Reviews")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // Kích hoạt auditing cho entity
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Evaluate_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "evaluate_evaluateid_seq", allocationSize = 1)
    private int reviewsId;
    @Column(name="content")
    private String content;
    @Column(name="time")
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser")
    private UserEntity userId=new UserEntity(); // id người đánh giá=> phải đăng nhập trước
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idDocotr")
    private DoctorEntity doctorId=new DoctorEntity();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idDatLich")
    private AppointmentEntity appointment;
}

