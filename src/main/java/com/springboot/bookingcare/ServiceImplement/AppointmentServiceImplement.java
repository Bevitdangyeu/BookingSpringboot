package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.AppointmentDTO;
import com.springboot.bookingcare.DTO.AppointmentRequest;
import com.springboot.bookingcare.Entity.AppointmentEntity;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.TimeEntity;
import com.springboot.bookingcare.Mapper.AppointmentMapper;
import com.springboot.bookingcare.Repository.AppointmentRepository;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Repository.TimeRepository;
import com.springboot.bookingcare.Service.AppointmentService;
import com.springboot.bookingcare.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImplement implements AppointmentService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TimeRepository timeRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    AppointmentMapper appointmentMapper;
    @Override
    public boolean addAppointment(AppointmentRequest appointmentRequest) {
       try {
           // lấy đối tượng bác sĩ và đối tượng lịch khám
           TimeEntity time=timeRepository.findByTimeId(appointmentRequest.getTime().getTimeId());
           DoctorEntity doctor=doctorRepository.findByDoctorId(appointmentRequest.getDoctor().getIdDoctor());
           AppointmentEntity appointmentEntity=new AppointmentEntity();
           appointmentEntity.setAddress(appointmentRequest.getAddress());
           appointmentEntity.setDoctor(doctor);
           appointmentEntity.setDescription(appointmentRequest.getDescription());
           appointmentEntity.setSex(appointmentRequest.getSex());
           appointmentEntity.setCreateAt(LocalDate.now());
           appointmentEntity.setDate(appointmentEntity.getDate());
           appointmentEntity.setFullName(appointmentRequest.getFullName());
           appointmentEntity.setImage(appointmentRequest.getImage());
           appointmentEntity.setDateOfBirth(appointmentRequest.getDateOfBirth());
           appointmentEntity.setPhoneNumber(appointmentRequest.getPhoneNumber());
           appointmentEntity.setStatus("Pending approval");
           appointmentEntity.setTime(time);
           appointmentEntity.setReviewed(true);
           appointmentEntity.setDate(appointmentRequest.getDate());
           appointmentRepository.save(appointmentEntity);
           return true;
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
       }
    }

    @Override
    public List<AppointmentDTO> findAllForUser(int id) {
        List<AppointmentDTO> appointmentDTOS=new ArrayList<>();
        List<AppointmentEntity> appointmentEntities=appointmentRepository.findAllForUser(id);
        for (AppointmentEntity appointment : appointmentEntities){
            appointmentDTOS.add(appointmentMapper.EntityToDTO(appointment));
        }
        return appointmentDTOS;
    }

    @Override
    public AppointmentDTO findByAppointmentId(int id) {
        return appointmentMapper.EntityToDTO(appointmentRepository.findByAppointmentId(id));
    }
}
