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
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.security.Principal;
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
    public ResponseEntity<Map<String,String>> add(@Payload ReviewsDTO reviewsDTO, SimpMessageHeaderAccessor headerAccessor){
        Map<String,String> response=new HashMap<>();
       try{
           Principal principal = (Principal) headerAccessor.getSessionAttributes().get("user");

           if (principal instanceof UsernamePasswordAuthenticationToken) {
               UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) principal;
               String userId = (String) authToken.getPrincipal(); // Lấy userId từ authToken
               System.out.println("id: "+userId);
               UserDTO userDTO = new UserDTO();
               userDTO.setIdUser(Integer.parseInt(userId));
               reviewsDTO.setUser(userDTO);
               reviewsDTO = reviewsService.add(reviewsDTO);
               response.put("Message", "Đánh giá đã được gửi");
               // gửi review đến người nhận
               System.out.println("✅ Gửi review đến: /topic/profile/" + reviewsDTO.getDoctor().getIdDoctor());
               System.out.println("✅ Gửi review đến: /topic/reviewer/" + reviewsDTO.getAppointment().getAppointmentId());
               messagingTemplate.convertAndSend("/topic/profile/" + reviewsDTO.getDoctor().getIdDoctor(), reviewsDTO);
               messagingTemplate.convertAndSend("/topic/reviewer/" + reviewsDTO.getAppointment().getAppointmentId(), response);
               return ResponseEntity.ok(response);
           }
         //  }
         //  else{
         //      return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
          // }
       } catch (Exception e) {
           response.put("Message","Thất bại! vui lòng gửi lại.");
           return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
       }
        return null;
    }
    @GetMapping("/public/getReviews/{id}")
    public List<ReviewsDTO> findByDoctorId(@PathVariable("id") int id){
        return reviewsService.findByDoctor(id);
    }
}
