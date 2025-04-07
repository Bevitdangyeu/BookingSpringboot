package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.ReviewRepliesDTO;
import com.springboot.bookingcare.Entity.ReviewRepliesEntity;
import com.springboot.bookingcare.Entity.ReviewsEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Mapper.ReviewRepliesMapper;
import com.springboot.bookingcare.Repository.ReviewsRepliesRepository;
import com.springboot.bookingcare.Repository.ReviewsRepository;
import com.springboot.bookingcare.Repository.UserRepository;
import com.springboot.bookingcare.Service.ReviewsRepliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewsRepliesServiceImplement implements ReviewsRepliesService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    ReviewsRepliesRepository reviewsRepliesRepository;
    @Autowired
    ReviewRepliesMapper reviewRepliesMapper;
    @Override
    public ReviewRepliesDTO add(ReviewRepliesDTO reviewRepliesDTO) {
        UserEntity user=userRepository.findByIdUser(reviewRepliesDTO.getUser().getIdUser());
        ReviewsEntity reviewsEntity=reviewsRepository.findByReviewsId(reviewRepliesDTO.getReviews().getReviewsId());
        ReviewRepliesEntity reviewRepliesEntity=new ReviewRepliesEntity();
        reviewRepliesEntity.setContent(reviewRepliesDTO.getContent());
        reviewRepliesEntity.setUser(user);
        reviewRepliesEntity.setReviews(reviewsEntity);
        reviewsRepliesRepository.save(reviewRepliesEntity);
        reviewRepliesDTO=reviewRepliesMapper.entityToDTO(reviewRepliesEntity);
        return reviewRepliesDTO;
    }

    @Override
    public List<ReviewRepliesDTO> findAll(int id) {
        List<ReviewRepliesDTO> reviewRepliesDTOList=new ArrayList<>();
        for(ReviewRepliesEntity reviewReplies:reviewsRepliesRepository.findAllByReviewsId(id)){
            reviewRepliesDTOList.add(reviewRepliesMapper.entityToDTO(reviewReplies));
        }
        return reviewRepliesDTOList;
    }

}
