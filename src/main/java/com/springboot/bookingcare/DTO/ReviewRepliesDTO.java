package com.springboot.bookingcare.DTO;

import com.springboot.bookingcare.Entity.ReviewsEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class ReviewRepliesDTO {
    private int idReviewReplies;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private UserDTO user;
    private ReviewsDTO reviews;
}
