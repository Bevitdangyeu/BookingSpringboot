package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.HospitalEntity;
import com.springboot.bookingcare.Entity.RoleEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Mapper.DoctorMapper;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Repository.HospitalRepository;
import com.springboot.bookingcare.Repository.RoleRepository;
import com.springboot.bookingcare.Repository.UserRepository;
import com.springboot.bookingcare.Service.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    RoleRepository roleRepository;
    //@Cacheable(value = "doctors",key = "'allDoctors'")
    // @CacheEvict(value = "doctors", key = "'allDoctors'") xóa cache khi có update
    //  @CachePut(value = "doctors", key = "'allDoctors'")    // Cập nhật lại cache
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
    @Override
    public DoctorDTO findByDoctorForEdit(int id) {
        return doctorMapper.EntityToDTO(doctorRepository.findByUserId(id));
    }

    @Override
    public List<DoctorDTO> findByExpertise(String expertise) {
        List<DoctorEntity> listDoctor=doctorRepository.findByExpertise(expertise);
        if(listDoctor!=null){
            List<DoctorDTO> listDoctorDTO=new ArrayList<>();
            for(DoctorEntity doctorEntity:listDoctor){
                listDoctorDTO.add(doctorMapper.EntityToDTO(doctorEntity));
            }
            return listDoctorDTO;
        }
        return null;
    }

    @Transactional
    @Override
    public DoctorDTO add(DoctorDTO doctor) {
        DoctorEntity doctorEntity=new DoctorEntity();
        UserEntity userEntity=new UserEntity();
        if(doctor.getIdDoctor()==0){
            userEntity.setPassword(passwordEncoder.encode(doctor.getUserDTO().getPassword()));
            userEntity.setUserName(doctor.getUserDTO().getUsername());
        }
        if(doctor.getIdDoctor()!=0){
            doctorEntity=doctorRepository.findByDoctorId(doctor.getIdDoctor());
            userEntity=userRepository.findByIdUser(doctor.getUserDTO().getIdUser());
            userEntity.setUserName(userEntity.getUserName());
            userEntity.setPassword(userEntity.getPassword());
        }
        HospitalEntity hospitalEntity=hospitalRepository.findByHospitalName(doctor.getHospital());
        RoleEntity role=roleRepository.findByRoleCode("DOCTOR");
        doctorEntity.setCertificate(doctor.getCertificate());
        doctorEntity.setDescription(doctor.getDescription());
        doctorEntity.setExperience(doctor.getExperience());
        doctorEntity.setExpertise(doctor.getExpertise());
        doctorEntity.setPhoneNumber(doctor.getPhoneNumber());
        doctorEntity.setHospitalId(hospitalEntity);
        doctorEntity.setShortDescription(doctor.getShortDescription());
        doctorEntity.setUser(userEntity);
        doctorRepository.save(doctorEntity);
        // thêm thông tin cho user
        userEntity.setImage(doctor.getUserDTO().getImage());
        userEntity.setAddress(doctor.getUserDTO().getAddress());
        userEntity.setEmail(doctor.getUserDTO().getAddress());
        userEntity.setFullName(doctor.getUserDTO().getFullName());
        userEntity.setDoctor(doctorEntity);
        userEntity.setRole(role);
        userEntity=userRepository.save(userEntity);

        return doctorMapper.EntityToDTO(doctorEntity);
    }
}
