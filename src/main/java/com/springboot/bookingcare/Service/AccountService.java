package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.UserDTO;

import java.util.List;

public interface AccountService {
    List<UserDTO> getAllUser();
    List<DoctorDTO> getAllDoctor();
    UserDTO lockUser(int id);
}
