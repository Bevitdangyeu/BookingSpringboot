package com.springboot.bookingcare.Config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetailService;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthInterceptor implements HandshakeInterceptor, ChannelInterceptor {

    @Autowired
    CustomeUserDetailService customeUserDetailService;
    private static final String SECRET_KEY ="test-key";
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        {
            return true;
        }

    }
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        StompCommand command = headerAccessor.getCommand();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        //Bỏ qua kiểm tra JWT khi CONNECT & SUBSCRIBE
        if (StompCommand.CONNECT.equals(command) || StompCommand.SUBSCRIBE.equals(command)) {
            return message;
        }
        // Kiểm tra JWT khi SEND message
        if (StompCommand.SEND.equals(command)) {
            String token = accessor.getFirstNativeHeader("Authorization");
            String refreshToken=accessor.getFirstNativeHeader("Authorization-Refresh");
            if (token == null || !token.startsWith("Bearer ")) {
                try {
                    return sendErrorResponse(accessor, " Token bị thiếu hoặc không hợp lệ!", 401);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            token = token.substring(7);
            String userId = new CustomJWT().extractUserId(token);
            try {
                System.out.println("vô đc preSendddd");
                int tokenStatus=0;
                tokenStatus=new CustomJWT().validateToken(token);
                System.out.println("trạng thái trogn preSend: "+tokenStatus);
                if(tokenStatus==401){
                    // kiểm tra refresh token
                    if (refreshToken != null && new CustomJWT().validateToken(refreshToken) == 200) {
                        return sendErrorResponse(accessor, "JWT không hợp lệ hoặc hết hạn!", 403);
                    } else {
                        throw new HandshakeFailureException("401 Unauthorized: Token expired, please login again");
                    }
                }
                if(tokenStatus==400){
                    return sendErrorResponse(accessor, " Lỗi xác thực JWT!", 401);
                }
                // token hợp lệ
                if (userId != null && new CustomJWT().validateToken(token) == 200) {
                    System.out.println("200");
                   try{
                       headerAccessor.setUser(SecurityContextHolder.getContext().getAuthentication());
                       UsernamePasswordAuthenticationToken authToken
                               =new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());
                       headerAccessor.getSessionAttributes().put("user", authToken);
                   }catch( Exception e){
                       System.out.println("Lỗi : " + e.getMessage());
                       e.printStackTrace();
                   }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return message;
    }
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
    // 🛑 Hàm gửi lỗi về client qua WebSocket
    private Message<?> sendErrorResponse(StompHeaderAccessor accessor, String errorMessage, int status) throws JsonProcessingException {
        Map<String, Object> errorPayload = new HashMap<>();
        errorPayload.put("status", status);
        errorPayload.put("message", errorMessage);

        MessageHeaders headers = accessor.getMessageHeaders();
        Message<byte[]> errorMessageObj = new GenericMessage<>(new ObjectMapper().writeValueAsBytes(errorPayload), headers);

      //  messagingTemplate.convertAndSendToUser(accessor.getSessionId(), "/queue/errors", errorMessageObj);
        return null; // Chặn message không hợp lệ
    }
}
