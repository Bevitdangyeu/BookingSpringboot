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
        if(userEntity.getRole().getRoleId()==3){
            userDto.setAddress(userEntity.getDoctor().getAdress());
            userDto.setDescription(userEntity.getDoctor().getDescription());
            userDto.setExperience(userEntity.getDoctor().getExperience());
            userDto.setExpertise(userEntity.getDoctor().getExpertise());
            userDto.setHospital(userEntity.getDoctor().getHospitalId().getHospitalName());
            userDto.setStar(userEntity.getDoctor().getStar());
            userDto.setCertificate(userEntity.getDoctor().getCertificate());
            userDto.setIdDoctor(userEntity.getDoctor().getDoctorId());
            userDto.setPhoneNumber(userEntity.getDoctor().getPhoneNumber());
        }
        return userDto;
    }
}
