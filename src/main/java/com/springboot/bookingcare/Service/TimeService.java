package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.TimeDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TimeService {
    public List<TimeDTO> getTimeByIdDoctor(int idDoctor, LocalDate time);
    public List<TimeDTO> findAll();
    public List<TimeDTO> findTimeForDoctor(int id);
    public List<TimeDTO> updateTime(int id,List<TimeDTO> time);
}
