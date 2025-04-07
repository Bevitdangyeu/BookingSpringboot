package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.PostDTO;
import com.springboot.bookingcare.Entity.PostEntity;

import java.util.List;
import java.util.Map;

public interface PostService {
    PostDTO add(PostDTO postDTO);
    public List<PostDTO> getListPost();
    public PostDTO findById(int id);
    public Map<String, Object> listPost(int id, int limit, int offset);
    public List<PostDTO> findTop10();
    public boolean deleteByPostId(int id);
}
