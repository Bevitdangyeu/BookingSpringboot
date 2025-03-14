package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.AppointmentRequest;

public interface AppoinmentService {
    public boolean addAppointment(AppointmentRequest appointmentRequest);
}
