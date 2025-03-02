package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;

import java.util.List;

public interface DoctorService {
    public List<DoctorDTO> findAllDoctor();
}
