package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;

import java.util.List;

public interface DoctorService {
    public List<DoctorDTO> findAllDoctor();
    public DoctorDTO findByDoctorId(int id);
    public DoctorDTO add(DoctorDTO doctor);
    public DoctorDTO findByDoctorForEdit(int id);
    public List<DoctorDTO> findByExpertise(String expertise);
}
