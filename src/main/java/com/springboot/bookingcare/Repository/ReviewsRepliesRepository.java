package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.ReviewRepliesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepliesRepository extends CrudRepository<ReviewRepliesEntity,Integer> {
    @Query("select r from ReviewRepliesEntity r where r.reviews.reviewsId = :id")
    List<ReviewRepliesEntity> findAllByReviewsId(@Param("id") int id);
}
