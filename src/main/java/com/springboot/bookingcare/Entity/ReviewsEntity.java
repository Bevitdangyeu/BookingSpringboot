package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Reviews",
        indexes = {
                @Index(name = "idx_Reviews_reviewsId", columnList = "reviewsId")
        })
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // Kích hoạt auditing cho entity
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_seq")
    @SequenceGenerator(name = "reviews_seq", sequenceName = "reviews_reviews_id_seq", allocationSize = 1)
    private int reviewsId;
    @Column(name="content")
    private String content;
    @Column(name="createAt")
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;
    @Column
    private int start;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser")
    private UserEntity userId=new UserEntity(); // id người đánh giá=> phải đăng nhập trước
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idDoctor")
    private DoctorEntity doctorId=new DoctorEntity();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="appointmentId")
    private AppointmentEntity appointment;
    @OneToMany(mappedBy = "reviews")
    private List<ReviewRepliesEntity> reviewReplies=new ArrayList<>();

}

