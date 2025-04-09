package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.CategoryDTO;
import com.springboot.bookingcare.Entity.CategoryEntity;
import com.springboot.bookingcare.Repository.CategoryRepository;
import com.springboot.bookingcare.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOList=new ArrayList<>();
        for (CategoryEntity categoryEntity: categoryRepository.findAll()){
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setCategoryId(categoryEntity.getCategoryId());
            categoryDTO.setCategoryName(categoryEntity.getCategoryName());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

}
