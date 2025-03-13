package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TimeDTO implements Serializable {
    private int timeId;
    private int hide;
    private String time;
}
