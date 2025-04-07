package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.ReviewRepliesDTO;
import com.springboot.bookingcare.Entity.ReviewRepliesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewRepliesMapper {
    @Autowired ReviewMapper reviewMapper;
    @Autowired UserMapper userMapper;
    public ReviewRepliesDTO entityToDTO(ReviewRepliesEntity reviewReplies){
        ReviewRepliesDTO reviewRepliesDTO=new ReviewRepliesDTO();
        reviewRepliesDTO.setIdReviewReplies(reviewReplies.getIdReviewReplies());
        reviewRepliesDTO.setReviews(reviewMapper.entityToDTO(reviewReplies.getReviews()));
        reviewRepliesDTO.setContent(reviewReplies.getContent());
        reviewRepliesDTO.setUser(userMapper.EntityToDTO(reviewReplies.getUser()));
        reviewRepliesDTO.setCreateAt(reviewReplies.getCreateAt());
        reviewRepliesDTO.setUpdateAt(reviewReplies.getUpdateAt());
        return reviewRepliesDTO;
    }
}
