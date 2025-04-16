package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.TimeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeRepository extends CrudRepository<TimeEntity,Integer> {
    @Query("select t from TimeEntity t JOIN t.doctor d where d.doctorId= :doctorId and t.timeId NOT IN (select t1.timeId from AppointmentEntity a JOIN TimeEntity t1 on t1.timeId = a.time.timeId where a.doctor.doctorId = :doctorId and a.date= :time) ")
    public List<TimeEntity> getTimeByIdAndTime(@Param("doctorId")int doctorId, @Param("time") LocalDate time);
    public TimeEntity findByTimeId(int timeId);
    @Query("select t from TimeEntity t Join t.doctor d where d.doctorId= :id")
    public List<TimeEntity> findTimeForDoctor(@Param("id") int id);

}
