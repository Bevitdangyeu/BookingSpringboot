package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.AppointmentDTO;
import com.springboot.bookingcare.DTO.AppointmentRequest;

import java.util.List;

public interface AppointmentService {
    public boolean addAppointment(AppointmentRequest appointmentRequest);
    public List<AppointmentDTO> findAllForUser(int id);
    public AppointmentDTO findByAppointmentId(int id);
    public List<AppointmentDTO> findAllByDoctorId(int id,String date);
    public AppointmentDTO updateStatus(int AppointmentId, String status);
}
