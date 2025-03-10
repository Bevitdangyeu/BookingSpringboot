package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.TimeDTO;
import com.springboot.bookingcare.Entity.TimeEntity;
import org.springframework.stereotype.Component;

@Component
public class TimeMapper {
    public TimeDTO EntityToDTO(TimeEntity time){
        TimeDTO timeDTO=new TimeDTO();
        timeDTO.setTimeId(time.getTimeId());
        timeDTO.setHide(time.getHide());
        timeDTO.setTime(time.getLichKham());
        return timeDTO;
    }
}
