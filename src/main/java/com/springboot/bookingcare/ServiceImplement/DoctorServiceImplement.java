package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Mapper.DoctorMapper;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class DoctorServiceImplement implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorMapper doctorMapper;

    @Cacheable(value = "doctors",key = "'allDoctors'")
    @Override
    public List<DoctorDTO> findAllDoctor() {
        System.out.println("Vào serivce để lấy danh sách bác sĩ vào lúc: "+ LocalDateTime.now());
        List<DoctorEntity> list=doctorRepository.findAll();
        List<DoctorDTO> doctorDTOList=new ArrayList<>();
        for(DoctorEntity doctorEntity:list){
            doctorDTOList.add(doctorMapper.EntityToDTO(doctorEntity));
        }
        return doctorDTOList;
    }

    @Override
    public DoctorDTO findByDoctorId(int id) {
        return doctorMapper.EntityToDTO(doctorRepository.findByDoctorId(id));
    }
}
