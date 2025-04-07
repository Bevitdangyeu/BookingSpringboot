package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.CategoryDTO;
import com.springboot.bookingcare.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CategoryAPI {
    @Autowired
    CategoryService categoryService;
    @GetMapping("public/category/findAll")
    public List<CategoryDTO> findAll(){
        return categoryService.findAll();
    }

}
