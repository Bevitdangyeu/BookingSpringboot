package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.ReviewsDTO;
import com.springboot.bookingcare.Entity.ReviewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    @Autowired AppointmentMapper appointmentMapper;
    @Autowired DoctorMapper doctorMapper;
    @Autowired UserMapper userMapper;
    public ReviewsDTO entityToDTO(ReviewsEntity reviewsEntity){
        ReviewsDTO reviewDTO=new ReviewsDTO();
        reviewDTO.setReviewsId(reviewsEntity.getReviewsId());
        reviewDTO.setAppointment(appointmentMapper.EntityToDTO(reviewsEntity.getAppointment()));
        reviewDTO.setStart(reviewsEntity.getStart());
        reviewDTO.setDoctor(doctorMapper.EntityToDTO(reviewsEntity.getDoctorId()));
        reviewDTO.setContent(reviewsEntity.getContent());
        reviewDTO.setUser(userMapper.EntityToDTO(reviewsEntity.getUserId()));
        reviewDTO.setCreateAt(reviewsEntity.getCreateAt());
        reviewDTO.setUpdateAt(reviewsEntity.getUpdateAt());
        return reviewDTO;
    }
}
