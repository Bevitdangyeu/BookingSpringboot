package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.DoctorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity,Integer> {
    List<DoctorEntity> findAll();
    DoctorEntity findByDoctorId(int doctorId);
    @Query("select d from DoctorEntity d where d.user.idUser= :idUser")
    DoctorEntity findByUserId(@Param("idUser") int idUser);
}
