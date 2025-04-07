package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> findAll();
}
