package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.ReviewsDTO;
import com.springboot.bookingcare.Entity.ReviewsEntity;

import java.util.List;

public interface ReviewsService {
    public ReviewsDTO add(ReviewsDTO rieview);
    public List<ReviewsDTO> findByDoctor(int id);
}
