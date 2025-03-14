package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.AppointmentRequest;
import com.springboot.bookingcare.Entity.AppointmentEntity;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.TimeEntity;
import com.springboot.bookingcare.Repository.AppointmentRepository;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Repository.TimeRepository;
import com.springboot.bookingcare.Service.AppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentServiceImplement implements AppoinmentService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TimeRepository timeRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
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
           appointmentEntity.setDate(appointmentRequest.getDate());
           appointmentRepository.save(appointmentEntity);
           return true;
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
       }
    }
}
