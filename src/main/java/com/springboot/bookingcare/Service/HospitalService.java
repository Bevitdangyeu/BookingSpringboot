package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.HospitalDTO;

import java.util.List;

public interface HospitalService {
    List<HospitalDTO> findAll(String province);
}
