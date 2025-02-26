package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Mapper.UserMapper;
import com.springboot.bookingcare.Repository.UserRepository;
import com.springboot.bookingcare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDTO findByUserName(String userName) {
        UserEntity userEntity= userRepository.findByUserName(userName);
        return userMapper.EntityToDTO(userEntity);
    }
}
