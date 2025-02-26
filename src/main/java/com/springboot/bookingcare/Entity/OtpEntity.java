package com.springboot.bookingcare.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="otp")
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_seq")
    @SequenceGenerator(name = "otp_seq", sequenceName = "otp_otpId_seq", allocationSize = 1)
    @Column(name="otpId")
    private int otpId;
    @Column(name="otp")
    private String otp;
    @Column(name="createAt")
    private LocalDateTime createAt;
    @Column(name="isUsed")
    private Integer isUsed;
    @Column(name="expiry")
    private LocalDateTime Expiry;
    @Column(name="attempts")
    private int attempts;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser")
    private UserEntity userId;
}
