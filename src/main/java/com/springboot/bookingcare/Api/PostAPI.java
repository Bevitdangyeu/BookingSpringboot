package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.DoctorDTO;
import com.springboot.bookingcare.DTO.PageableDTO;
import com.springboot.bookingcare.DTO.PostDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Service.PostService;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostAPI {
    @Autowired
    PostService postService;
    @PostMapping("/doctor/post/add")
    public ResponseEntity<Map<String,Object>> addPost(@RequestBody PostDTO post){
        Map<String,Object> response=new HashMap<>();
        // lấy thông tin của bác sĩ
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails user=(UserDetails) authentication.getPrincipal();
            // ép user về customUserDetail
            CustomeUserDetails custome=(CustomeUserDetails) user;
            UserDTO userDTO=new UserDTO();
            userDTO.setIdUser(custome.getUser().getIdUser());
            DoctorDTO doctorDTO=new DoctorDTO();
            doctorDTO.setUserDTO(userDTO);
            post.setDoctorDTO(doctorDTO);
            post=postService.add(post);
            response.put("post",post);
            response.put("message","Thêm bài viết thành công");
            return ResponseEntity.ok(response);
        }else{
            response.put("post",null);
            response.put("message","Thêm bài viết thất bại");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
    @GetMapping("/public/post")
    public List<PostDTO> findPostByLike(){
        List<PostDTO> postDTOS=postService.findTop10();
        if(postDTOS!=null){
           return postDTOS;
        }
        else{
            return null;
        }
    }
    @GetMapping("/doctor/listPost/{page}")
    public PageableDTO listPostPageable(@PathVariable("page") int page) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails user=(UserDetails) authentication.getPrincipal();
            // ép user về customUserDetail
            CustomeUserDetails custome=(CustomeUserDetails) user;
            int id=custome.getUser().getIdUser();
            PageableDTO pageableDTO=new PageableDTO();
            Map<String, Object> listPost=postService.listPost(id,4, page);
            pageableDTO.setCurrentPage(page);
            pageableDTO.setListPostDTO(((List<PostDTO>) listPost.get("posts")));
            pageableDTO.setTotalPages((int)listPost.get("totalPage") );
            System.out.println("bài viết: "+pageableDTO);
            return pageableDTO;
        }
        return null;
    }
    @DeleteMapping("/doctor/post/delete/{id}")
    public ResponseEntity<Map<String,String>> deletePost(@PathVariable("id") int id){
        boolean status=postService.deleteByPostId(id);
        Map<String,String> response=new HashMap<>();
        if(status){
            response.put("message","return về xóa thành công");
            return ResponseEntity.ok(response);

        }else{
            response.put("message","Xóa bài viết thất bại");
            return ResponseEntity.badRequest().body(response);
        }
    }
    // lấy lên post theo id
    @GetMapping("/public/post/{id}")
    public ResponseEntity<Map<String,Object>> getPostById(@PathVariable("id")int id){
        Map<String,Object> response=new HashMap<>();
        if (id <= 0) {
            response.put("message","Id không hợp lệ");
            response.put("post",null);
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
        PostDTO postDTO = postService.findByPostId(id);
        if (postDTO == null) {
            response.put("message","Không tìm thấy bài viết với id"+id);
            response.put("post",null);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
        response.put("message","Lấy bài viết thành công");
        response.put("post",postDTO);
        return ResponseEntity.ok(response);
    }
    // lấy ra bài viết theo mã chủ đề và ngày đăng tải
    @GetMapping("/doctor/post/{categoryId}/{date}/{page}")
    public PageableDTO filterByCategoryAndDate(@PathVariable("categoryId")int categoryId, @PathVariable("date")String date,@PathVariable("page") int page) {
        // lấy lên thông tin user hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            // ép user về customUserDetail
            CustomeUserDetails custome = (CustomeUserDetails) user;
            int id = custome.getUser().getIdUser();
            PageableDTO pageableDTO = new PageableDTO();
            Map<String, Object> listPost = postService.findByCategoryAndDate(categoryId,date,id, 4, page);
            pageableDTO.setCurrentPage(page);
            pageableDTO.setListPostDTO(((List<PostDTO>) listPost.get("posts")));
            pageableDTO.setTotalPages((int)listPost.get("totalPage") );
            System.out.println(" số lượng sau khi lọc "+pageableDTO.getListPostDTO().size());
            for(PostDTO postDTO : pageableDTO.getListPostDTO()){
                System.out.println("Mã bài viết: "+postDTO.getPostId());
            }
            return pageableDTO;
        }
        return null;
    }
    @GetMapping("/public/post/category/{id}/{page}")
    public PageableDTO findByCategory(@PathVariable("id")int id,@PathVariable("page") int page){
        Map<String, Object> listPost = postService.findByCategory(id,10,page);
        PageableDTO pageableDTO = new PageableDTO();
        pageableDTO.setCurrentPage(page);
        pageableDTO.setListPostDTO(((List<PostDTO>) listPost.get("posts")));
        pageableDTO.setTotalPages((int)listPost.get("totalPage") );
        System.out.println(" số lượng sau khi lọc "+pageableDTO.getListPostDTO().size());
        for(PostDTO postDTO : pageableDTO.getListPostDTO()){
            System.out.println("Mã bài viết: "+postDTO.getPostId());
        }
        return pageableDTO;
    }
}
