package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.ReviewRepliesDTO;

import java.util.List;

public interface ReviewsRepliesService {
    ReviewRepliesDTO add(ReviewRepliesDTO reviewRepliesDTO);
    public List<ReviewRepliesDTO> findAll(int id);
}
