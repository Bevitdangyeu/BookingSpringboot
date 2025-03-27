package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.AppointmentDTO;
import com.springboot.bookingcare.DTO.ReviewsDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Service.ReviewsService;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewsAPI {
    @Autowired
    ReviewsService reviewsService;
    private final SimpMessagingTemplate messagingTemplate;

    public ReviewsAPI(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @MessageMapping("/user/review/add")
    public ResponseEntity<Map<String,String>> add(@Payload ReviewsDTO reviewsDTO){
        Map<String,String> response=new HashMap<>();
       try{
           Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//         //  if(authentication!=null){
//               UserDetails user=(UserDetails) authentication.getPrincipal();
//               // ép user về customeUserDetail
//               CustomeUserDetails custome=(CustomeUserDetails) user;
//               int id=custome.getUser().getIdUser();
//               UserDTO userDTO=new UserDTO();
//               userDTO.setIdUser(id);
//               reviewsDTO.setUser(userDTO);
               reviewsDTO= reviewsService.add(reviewsDTO);
               response.put("Message","Đánh giá đã được gửi");
               // gửi review đến người nhận
                System.out.println("✅ Gửi review đến: /topic/profile/" + reviewsDTO.getDoctor().getIdDoctor());
               messagingTemplate.convertAndSend("/topic/profile/"+reviewsDTO.getDoctor().getIdDoctor(),reviewsDTO);
               return ResponseEntity.ok(response);
         //  }
         //  else{
         //      return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
          // }
       } catch (Exception e) {
           response.put("Message","Thất bại! vui lòng gửi lại.");
           return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
       }
    }
    @GetMapping("/public/getReviews/{id}")
    public List<ReviewsDTO> findByDoctorId(@PathVariable int id){
        return reviewsService.findByDoctor(id);
    }
}
