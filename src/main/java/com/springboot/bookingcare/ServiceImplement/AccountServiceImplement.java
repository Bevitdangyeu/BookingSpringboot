package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Mapper.DoctorMapper;
import com.springboot.bookingcare.Mapper.UserMapper;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Repository.UserRepository;
import com.springboot.bookingcare.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AccountServiceImplement implements AccountService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @Override
    public List<UserDTO> getAllUser() {
        List<UserEntity> listUserEntityList =userRepository.findByActiveTrue();
        if(listUserEntityList!=null){
            List<UserDTO> listUser=new ArrayList<>();
            for(UserEntity user:listUserEntityList) {
                listUser.add(userMapper.EntityToDTO(user));
            }
            return listUser;
        }
        return null;
    }

    @Override
    public List<DoctorDTO> getAllDoctor() {
        List<DoctorEntity> listDoctor=doctorRepository.findAll();
        if(listDoctor!=null){
            List<DoctorDTO> listDoctorDTO=new ArrayList<>();
            for(DoctorEntity doctorEntity:listDoctor){
                listDoctorDTO.add(doctorMapper.EntityToDTO(doctorEntity));
            }
            return listDoctorDTO;
        }
        return null;
    }

    @Override
    public UserDTO lockUser(int id) {
       try{
           UserEntity user=userRepository.findByIdUser(id);
           user.setActive(!user.isActive());
           userRepository.save(user);
           return userMapper.EntityToDTO(user);
       } catch (Exception e) {
           throw new RuntimeException(e);

       }
    }

}
