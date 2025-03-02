package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public DoctorDTO EntityToDTO(DoctorEntity doctorEntity){
        UserDTO userDto=new UserDTO();
        DoctorDTO doctorDTO=new DoctorDTO();
        userDto.setIdUser(doctorEntity.getUser().getIdUser());
        userDto.setEmail(doctorEntity.getUser().getEmail());
        userDto.setImage(doctorEntity.getUser().getImage());
        userDto.setCoverPhoto(doctorEntity.getUser().getCoverPhoto());
        userDto.setFullName(doctorEntity.getUser().getFullName());
        userDto.setRole(doctorEntity.getUser().getRole().getRoleCode());
        userDto.setAddress(doctorEntity.getUser().getAddress());

        doctorDTO.setDescription(doctorEntity.getDescription());
        doctorDTO.setExperience(doctorEntity.getExperience());
        doctorDTO.setExpertise(doctorEntity.getExpertise());
        doctorDTO.setHospital(doctorEntity.getHospitalId().getHospitalName());
        doctorDTO.setStar(doctorEntity.getStar());
        doctorDTO.setCertificate(doctorEntity.getCertificate());
        doctorDTO.setIdDoctor(doctorEntity.getDoctorId());
        doctorDTO.setPhoneNumber(doctorEntity.getPhoneNumber());
        doctorDTO.setUserDTO(userDto);
        return doctorDTO;
    }
}
