package com.springboot.bookingcare.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PageableDTO {
    private int totalPages;
    private int currentPage;
    private List<PostDTO> listPostDTO;
}
