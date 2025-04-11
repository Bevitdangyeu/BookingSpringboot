package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.DTO.AppointmentDTO;
import com.springboot.bookingcare.Entity.AppointmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentEntity,Integer> {
    @Query("select a from AppointmentEntity a where a.user.idUser= :id")
    List<AppointmentEntity> findAllForUser(@Param("id") int id);
    AppointmentEntity findByAppointmentId(int appointmentId);
    @Query("select a from AppointmentEntity a where a.doctor.doctorId= :doctorId and a.date BETWEEN :date AND :date order by a.createAt desc")
    List<AppointmentEntity> findAllByDoctorId(@Param("doctorId") int doctorId, @Param("date") LocalDate date);
}
