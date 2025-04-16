package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.TimeDTO;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.TimeEntity;
import com.springboot.bookingcare.Mapper.TimeMapper;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Repository.TimeRepository;
import com.springboot.bookingcare.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public List<TimeDTO> getTimeByIdDoctor(int idDoctor,LocalDate time) {
        List<TimeDTO> timeDTOList=new ArrayList<>();
        List<TimeEntity> timeEntityList=timeRepository.getTimeByIdAndTime(idDoctor,time);
        for(TimeEntity timeEntity:timeEntityList){
            timeDTOList.add(timeMapper.EntityToDTO(timeEntity));
        }
        return timeDTOList;
    }

    @Override
    public List<TimeDTO> findAll() {
        List<TimeDTO> timeDTOList=new ArrayList<>();
        for(TimeEntity timeEntity: timeRepository.findAll()){
            TimeDTO time=new TimeDTO();
            time.setHide(timeEntity.getHide());
            time.setTimeId(timeEntity.getTimeId());
            time.setTime(timeEntity.getLichKham());
            timeDTOList.add(time);
        }
        return timeDTOList ;
    }

    @Override
    public List<TimeDTO> findTimeForDoctor(int id) {
        DoctorEntity doctorEntity=doctorRepository.findByUserId(id);
        List<TimeDTO> timeDTOList=new ArrayList<>();
        for(TimeEntity timeEntity: timeRepository.findTimeForDoctor(doctorEntity.getDoctorId())){
            TimeDTO time=new TimeDTO();
            time.setHide(timeEntity.getHide());
            time.setTimeId(timeEntity.getTimeId());
            time.setTime(timeEntity.getLichKham());
            timeDTOList.add(time);
        }
        return timeDTOList ;
    }

    @Override
    public List<TimeDTO> updateTime(int id,List<TimeDTO> times) {
        DoctorEntity doctorEntity=doctorRepository.findByUserId(id);
        // xoad liên kết giữa time và doctor
        for(TimeEntity time: doctorEntity.getTime()){
            time.getDoctor().remove(doctorEntity);
            timeRepository.save(time);
        }
        doctorEntity.getTime().clear(); // Xóa danh sách time ở doctor
        doctorRepository.save(doctorEntity); // Lưu doctor đã xóa liên kết
        List<TimeEntity> timeEntities=new ArrayList<>();
        for(TimeDTO timeDTO:times){
            TimeEntity timeEntity=timeRepository.findByTimeId(timeDTO.getTimeId());
            timeEntity.getDoctor().add(doctorEntity);
            timeRepository.save(timeEntity);
            timeEntities.add(timeEntity);
        }
        doctorEntity.setTime(timeEntities);
        doctorRepository.save(doctorEntity);
        for(TimeEntity timeEntity:doctorEntity.getTime()){
            TimeDTO timeDTO=new TimeDTO();
            timeDTO.setTimeId(timeEntity.getTimeId());
            timeDTO.setHide(timeEntity.getHide());
            timeDTO.setTime(timeEntity.getLichKham());
            times.add(timeDTO);
        }
        return times;
    }
}
