package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.ReviewsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends CrudRepository<ReviewsEntity,Integer> {
    @Query("select r from ReviewsEntity r where r.doctorId.doctorId= :id")
    public List<ReviewsEntity> findByDoctor(@Param("id") int id);

}
