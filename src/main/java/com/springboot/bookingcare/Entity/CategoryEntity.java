package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="Category",
        indexes = {
                @Index(name = "idx_Category_categoryId", columnList = "categoryId")
        })
public class CategoryEntity {
    @Id
    @Column(name="categoryId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_categoryId_seq", allocationSize = 1)
    private int categoryId;
    private String categoryName;
    @OneToMany(mappedBy = "categoryEntity")
    private List<PostEntity> postEntityList=new ArrayList<>();
}
