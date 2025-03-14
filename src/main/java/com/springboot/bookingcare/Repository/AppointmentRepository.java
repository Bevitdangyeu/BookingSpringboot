package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentEntity,Integer> {

}
