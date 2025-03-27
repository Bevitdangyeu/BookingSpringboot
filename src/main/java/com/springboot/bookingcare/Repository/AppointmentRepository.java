package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.DTO.AppointmentDTO;
import com.springboot.bookingcare.Entity.AppointmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentEntity,Integer> {
    @Query("select a from AppointmentEntity a where a.user.idUser= :id")
    List<AppointmentEntity> findAllForUser(@Param("id") int id);
    AppointmentEntity findByAppointmentId(int appointmentId);
}
