package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity()
@Table(name="userInfomation")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_idUser_seq", allocationSize = 1)
    @Column(name="idUser")
    private int idUser;
    @Column(name="fullName")
    private String fullName;
    @Column(name="image")
    private String image;
    @Column(name="email")
    private String email;
    @Column(name="coverPhoto")
    private String coverPhoto;
    @Column(name="userName",unique = true)
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="address")
    private String address;
    @OneToOne(mappedBy="userId",fetch = FetchType.LAZY)
    private OtpEntity otp;
    @OneToOne(mappedBy="user",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private DoctorEntity doctor;
    @OneToMany(mappedBy = "user")
    private List<AppointmentEntity> appointment=new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="roleId")
    private RoleEntity role=new RoleEntity();
    @OneToMany(mappedBy = "userId")
    private List<ReviewsEntity> reviewsList=new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<ReviewRepliesEntity> reviewRepliesList=new ArrayList<>();

}
