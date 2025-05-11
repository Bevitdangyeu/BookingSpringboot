package com.springboot.bookingcare.ServiceImplement;


import com.springboot.bookingcare.DTO.ReviewsDTO;
import com.springboot.bookingcare.Entity.AppointmentEntity;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.ReviewsEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Mapper.ReviewMapper;
import com.springboot.bookingcare.Repository.AppointmentRepository;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Repository.ReviewsRepository;
import com.springboot.bookingcare.Repository.UserRepository;
import com.springboot.bookingcare.Service.ReviewsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewsServiceImplement implements ReviewsService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    ReviewMapper reviewMapper;
    @Transactional
    @Override
    public ReviewsDTO add(ReviewsDTO reviewsDTO) {
        ReviewsEntity reviewsEntity;
        if(reviewsDTO.getReviewsId()!=0){
            reviewsEntity=reviewsRepository.findByReviewsId(reviewsDTO.getReviewsId());
        }
        else {
            reviewsEntity = new ReviewsEntity();
        }
        AppointmentEntity appointment=appointmentRepository.findByAppointmentId(reviewsDTO.getAppointment().getAppointmentId());
        UserEntity user=userRepository.findByIdUser(reviewsDTO.getUser().getIdUser());
        DoctorEntity doctor=doctorRepository.findByDoctorId(reviewsDTO.getDoctor().getIdDoctor());
        reviewsEntity.setUserId(user);
        reviewsEntity.setDoctorId(doctor);
        reviewsEntity.setAppointment(appointment);
        reviewsEntity.setStart(reviewsDTO.getStart());
        reviewsEntity.setContent(reviewsDTO.getContent());
        try {
            reviewsRepository.save(reviewsEntity);
            appointment.setReviewed(true);
            return reviewMapper.entityToDTO(reviewsEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ReviewsDTO> findByDoctor(int id) {
        List<ReviewsEntity> reviewsEntity=reviewsRepository.findByDoctor(id);
        List<ReviewsDTO> reviewsDTOS=new ArrayList<>();
        for(ReviewsEntity reivew:reviewsEntity){
           reviewsDTOS.add(reviewMapper.entityToDTO(reivew));
        }
        return reviewsDTOS;
    }
}
