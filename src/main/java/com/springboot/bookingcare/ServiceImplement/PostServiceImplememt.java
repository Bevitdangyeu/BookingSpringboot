package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.DTO.PostDTO;
import com.springboot.bookingcare.Entity.CategoryEntity;
import com.springboot.bookingcare.Entity.DoctorEntity;
import com.springboot.bookingcare.Entity.PostEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Mapper.DoctorMapper;
import com.springboot.bookingcare.Mapper.PostMapper;
import com.springboot.bookingcare.Repository.CategoryRepository;
import com.springboot.bookingcare.Repository.DoctorRepository;
import com.springboot.bookingcare.Repository.PostRepository;
import com.springboot.bookingcare.Repository.UserRepository;
import com.springboot.bookingcare.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImplememt implements PostService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostMapper postMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public PostDTO add(PostDTO post) {
        // chuyển về post trước khi thêm
        LocalDateTime currentDateTime = LocalDateTime.now();
        PostEntity postEntity;
        if(post.getPostId()==0){ // thực hiện thêm
            postEntity = new PostEntity();
        }
        // thực hiện sửa
        else{
            postEntity=postRepository.findByPostId(post.getPostId());
        }
        CategoryEntity category=categoryRepository.findByCategoryId(post.getCategory().getCategoryId());
        DoctorEntity doctorEntity=doctorRepository.findByUserId(post.getDoctorDTO().getUserDTO().getIdUser());
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setCreateAt(currentDateTime);
        postEntity.setImage(post.getImage());
        postEntity.setDoctor(doctorEntity);
        postEntity.setCategoryEntity(category);
        postEntity.setImage(post.getImage());
        postEntity= postRepository.save(postEntity);
        post=postMapper.EntityToDTO(postEntity);
        return post;
    }
    // hàm lấy lên tất cả bài viết
    @Override
    public List<PostDTO> getListPost() {
        List <PostDTO> listPost=new ArrayList<>();
        List<Integer> ids= postRepository.findLatestPostIds(PageRequest.of(0, 8));
        List<PostEntity> listPostEntity=postRepository.findListPost(ids);
        for (PostEntity post: listPostEntity){
            listPost.add(postMapper.EntityToDTO(post));
        }
        return listPost;
    }

    @Override
    public PostDTO findById(int id) {
        PostEntity postEntity=postRepository.findByPostId(id);
        return postMapper.EntityToDTO(postEntity);
    }

    @Override
    public Map<String, Object> listPost(int id, int limit, int offset) {
        Map<String, Object> response = new HashMap<>();
        UserEntity user=userRepository.findByIdUser(id);
        // tạo một đối tượng phân trang
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("createAt").descending());
        Page<PostEntity> postPage = postRepository.findPostsPageable(user.getDoctor().getDoctorId(),pageable);
        List<PostEntity> posts = postPage.getContent();
        List<PostDTO> listPostDTO=new ArrayList<>();
        for( PostEntity post: posts){
            listPostDTO.add(postMapper.EntityToDTO(post));
        }
        response.put("posts", listPostDTO);
        response.put("totalPage",postPage.getTotalPages() );
        return response;
    }
    @Override
    // lấy lên tất cả bài viết cho trang chủ
    public List<PostDTO> findTop10(){
        List<PostDTO> postDTOList=new ArrayList<>();
        for(PostEntity postEntity : postRepository.PostsPublic()){
            postDTOList.add(postMapper.EntityToDTO(postEntity));
        }
        return postDTOList;
    }

}
