package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.HospitalDTO;
import com.springboot.bookingcare.Entity.HospitalEntity;
import org.springframework.stereotype.Component;

@Component
public class HospitalMapper {
    public HospitalDTO EntityToDTO(HospitalEntity hospital){
        HospitalDTO hospitalDTO=new HospitalDTO();
        hospitalDTO.setHospitalId(hospital.getHospitalId());
        hospitalDTO.setHospitalName(hospital.getHospitalName());
        hospitalDTO.setImage(hospital.getImage());
        hospitalDTO.setAddress(hospital.getAddress());
        return hospitalDTO;
    }
}
