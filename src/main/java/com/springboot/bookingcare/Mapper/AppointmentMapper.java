package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.AppointmentDTO;
import com.springboot.bookingcare.DTO.TimeDTO;
import com.springboot.bookingcare.Entity.AppointmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppointmentMapper {
    @Autowired DoctorMapper doctorMapper;
    public AppointmentDTO EntityToDTO(AppointmentEntity appointmentEntity){
        // tạo đối tượng timeDTO
        TimeDTO time=new TimeDTO();
        time.setTimeId(appointmentEntity.getTime().getTimeId());
        time.setTime(appointmentEntity.getTime().getLichKham());
        time.setHide(appointmentEntity.getTime().getHide());
        AppointmentDTO appointmentDTO=new AppointmentDTO();
        appointmentDTO.setDoctor(doctorMapper.EntityToDTO(appointmentEntity.getDoctor()));
        appointmentDTO.setTime(time);
        appointmentDTO.setAppointmentId(appointmentEntity.getAppoinmentId());
        appointmentDTO.setDescription(appointmentEntity.getDescription());
        appointmentDTO.setSex(appointmentEntity.getSex());
        appointmentDTO.setCreateAt(appointmentEntity.getCreateAt());
        appointmentDTO.setFullName(appointmentEntity.getFullName());
        appointmentDTO.setImage(appointmentEntity.getImage());
        appointmentDTO.setDateOfBirth(appointmentEntity.getDateOfBirth());
        appointmentDTO.setPhoneNumber(appointmentEntity.getPhoneNumber());
        appointmentDTO.setStatus(appointmentEntity.getStatus());
        return appointmentDTO;
    }
}
