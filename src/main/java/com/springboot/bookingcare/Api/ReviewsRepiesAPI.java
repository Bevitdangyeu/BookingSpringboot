package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.DTO.ReviewRepliesDTO;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Service.ReviewsRepliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewsRepiesAPI {
    @Autowired
    ReviewsRepliesService reviewsRepliesService;
    private final SimpMessagingTemplate messagingTemplate;
    public ReviewsRepiesAPI(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @GetMapping("/public/replies/findAll/{reviewId}")
    public List<ReviewRepliesDTO> findAll(@PathVariable("reviewId") int reviewId){
        return reviewsRepliesService.findAll(reviewId);
    }
    @MessageMapping("/user/replies/add")
    public ResponseEntity<Map<String,String>> add(@Payload ReviewRepliesDTO reviewRepliesDTO, SimpMessageHeaderAccessor headerAccessor){
        System.out.println("📩 Nhận dữ liệu từ client: " + reviewRepliesDTO);
        Map<String,String> response=new HashMap<>();
        try{
            Principal principal = (Principal) headerAccessor.getSessionAttributes().get("user");

            if (principal instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) principal;
                String userId = (String) authToken.getPrincipal(); // Lấy userId từ authToken
                System.out.println("id: "+userId);
                UserDTO userDTO = new UserDTO();
                userDTO.setIdUser(Integer.parseInt(userId));
                reviewRepliesDTO.setUser(userDTO);
                reviewRepliesDTO = reviewsRepliesService.add(reviewRepliesDTO);
                response.put("Message", "Đánh giá đã được gửi");
                // gửi review đến người nhận
                System.out.println("✅ Gửi replies đến: /topic/replies/" + reviewRepliesDTO.getReviews().getReviewsId());
              //System.out.println("✅ Gửi review đến: /topic/reviewer/" + reviewsDTO.getAppointment().getAppointmentId());
                messagingTemplate.convertAndSend("/topic/replies/" +reviewRepliesDTO.getReviews().getReviewsId(),reviewRepliesDTO);
              //  messagingTemplate.convertAndSend("/topic/reviewer/" + reviewsDTO.getAppointment().getAppointmentId(), response);
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
}
