package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PostDTO {
    private int postId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String image;
    private String tag;
    private DoctorDTO doctorDTO;
    private int like;
    private String type;
    private CategoryDTO category;
}
