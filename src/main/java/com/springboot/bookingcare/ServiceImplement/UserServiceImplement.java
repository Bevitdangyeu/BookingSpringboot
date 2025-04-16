package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.RoleEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Mapper.UserMapper;
import com.springboot.bookingcare.Repository.RoleRepository;
import com.springboot.bookingcare.Repository.UserRepository;
import com.springboot.bookingcare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserDTO findByUserName(String userName) {
        UserEntity userEntity= userRepository.findByUserName(userName);
        return userMapper.EntityToDTO(userEntity);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        RoleEntity role=roleRepository.findByRoleCode("USER");
        UserEntity userEntity=new UserEntity();
        userEntity.setFullName(userDTO.getFullName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUserName(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRole(role);
        userEntity.setImage("avtDefault.jpg");
        return userMapper.EntityToDTO(userRepository.save(userEntity));
    }
}
