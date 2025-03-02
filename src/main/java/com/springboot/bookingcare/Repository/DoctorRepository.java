package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.DoctorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity,Integer> {
    List<DoctorEntity> findAll();
}
