package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="ReviewReplies")
@EntityListeners(AuditingEntityListener.class) // Kích hoạt auditing cho entity
public class ReviewReplies {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ReviewReplies_seq")
    @SequenceGenerator(name="ReviewReplies_seq",sequenceName = "ReviewReplies_id_seq")
    private int idReviewReplies;
    private String content;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idUser")
    private UserEntity user=new UserEntity();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="reviewsId")
    private ReviewsEntity reviewsEntity=new ReviewsEntity();

}
