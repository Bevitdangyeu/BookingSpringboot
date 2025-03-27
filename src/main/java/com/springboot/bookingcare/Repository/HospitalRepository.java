package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.HospitalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends CrudRepository<HospitalEntity,Integer> {
    HospitalEntity findByHospitalName(String hospitalName);
    @Query("SELECT h FROM HospitalEntity h WHERE LOWER(h.address) LIKE LOWER(CONCAT('%', :province, '%'))")
    List<HospitalEntity> searchByAddress(@Param("province") String province);
}
