package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO EntityToDTO(UserEntity userEntity){
        UserDTO userDto=new UserDTO();
        userDto.setIdUser(userEntity.getIdUser());
        userDto.setEmail(userEntity.getEmail());
        userDto.setImage(userEntity.getImage());
        userDto.setCoverPhoto(userEntity.getCoverPhoto());
        userDto.setFullName(userEntity.getFullName());
        userDto.setRole(userEntity.getRole().getRoleCode());
        userDto.setAddress(userEntity.getAddress());
        return userDto;
    }
}
