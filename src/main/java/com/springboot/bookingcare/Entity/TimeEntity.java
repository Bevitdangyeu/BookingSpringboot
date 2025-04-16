package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Collate;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Table(name="time")
@Entity
public class TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_seq")
    @SequenceGenerator(name = "time_seq", sequenceName = "time_timeid_seq", allocationSize = 1)
    @Column(name="timeId")
    private int timeId;
    @Column(name="lichKham")
    private String lichKham;
    @Column(name="hide")
    private int hide;
    // quan hệ nhiều nhiều với bác sĩ
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Doctor_LichKham",
            joinColumns=@JoinColumn(name="idLichKham"),
            inverseJoinColumns = @JoinColumn(name="idDoctor")
    )
    private List<DoctorEntity> doctor=new ArrayList<DoctorEntity>();
    @OneToMany(mappedBy="time")
    private List<AppointmentEntity> time=new ArrayList<AppointmentEntity>();
}
