package com.springboot.bookingcare.Mapper;

import com.springboot.bookingcare.DTO.CategoryDTO;
import com.springboot.bookingcare.DTO.PostDTO;
import com.springboot.bookingcare.Entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    @Autowired DoctorMapper doctorMapper;
    public PostDTO EntityToDTO(PostEntity postEntity){
        PostDTO postDTO=new PostDTO();
        postDTO.setPostId(postEntity.getPostId());
        postDTO.setLike(postEntity.getPostId());
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setCategoryId(postEntity.getCategoryEntity().getCategoryId());
        categoryDTO.setCategoryName(postEntity.getCategoryEntity().getCategoryName());
        postDTO.setCategory(categoryDTO);
        postDTO.setContent(postEntity.getContent());
        postDTO.setImage(postEntity.getImage());
        postDTO.setDoctorDTO(doctorMapper.EntityToDTO(postEntity.getDoctor()));
        postDTO.setCreateAt(postEntity.getCreateAt());
        postDTO.setUpdateAt(postEntity.getUpdateAt());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setLike(postEntity.getLikePost());
        postDTO.setTag((postDTO.getTag()));
        return postDTO;
    }
}
