package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.TimeDTO;
import com.springboot.bookingcare.Entity.TimeEntity;
import com.springboot.bookingcare.Mapper.TimeMapper;
import com.springboot.bookingcare.Repository.TimeRepository;
import com.springboot.bookingcare.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeServiceImplement implements TimeService {
    @Autowired
    TimeRepository timeRepository;
    @Autowired
    TimeMapper timeMapper;
    @Override
    public List<TimeDTO> getTimeByIdDoctor(int idDoctor,LocalDate time) {
        List<TimeDTO> timeDTOList=new ArrayList<>();
        List<TimeEntity> timeEntityList=timeRepository.getTimeByIdAndTime(idDoctor,time);
        for(TimeEntity timeEntity:timeEntityList){
            timeDTOList.add(timeMapper.EntityToDTO(timeEntity));
        }
        return timeDTOList;
    }
}
