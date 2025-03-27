package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.HospitalDTO;
import com.springboot.bookingcare.Entity.HospitalEntity;
import com.springboot.bookingcare.Mapper.HospitalMapper;
import com.springboot.bookingcare.Repository.HospitalRepository;
import com.springboot.bookingcare.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalServiceImplement implements HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    HospitalMapper hospitalMapper;
    @Override
    public List<HospitalDTO> findAll(String province) {
        List<HospitalDTO>hospitalDTOS=new ArrayList<>();
        for(HospitalEntity hospitalEntity: hospitalRepository.searchByAddress(province)){
            hospitalDTOS.add(hospitalMapper.EntityToDTO(hospitalEntity));
        }
        return hospitalDTOS;
    }
}
