package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Mapper.DoctorMapper;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DoctorServiceImplement implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorMapper doctorMapper;
    @Override
    public List<DoctorDTO> findAllDoctor() {
        List<DoctorEntity> list=doctorRepository.findAll();
        List<DoctorDTO> doctorDTOList=new ArrayList<>();
        for(DoctorEntity doctorEntity:list){
            doctorDTOList.add(doctorMapper.EntityToDTO(doctorEntity));
        }
        return doctorDTOList;
    }
}
