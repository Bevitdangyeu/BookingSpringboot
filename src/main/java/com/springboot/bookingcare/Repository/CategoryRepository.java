package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {
    List<CategoryEntity> findAll();
    CategoryEntity findByCategoryId(int categoryId);
}
