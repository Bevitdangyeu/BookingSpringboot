package com.springboot.bookingcare.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthInterceptor implements HandshakeInterceptor, ChannelInterceptor {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private static final String SECRET_KEY ="test-key";
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        {
            String queryString = request.getURI().getQuery();
            if (queryString == null) {
                return false; // Nếu không có token, từ chối kết nối
            }
            Map<String, String> queryParams = new HashMap<>();
            String[] pairs = queryString.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    queryParams.put(keyValue[0], keyValue[1]);
                }
            }

            String token = queryParams.get("token");
            String refreshToken = queryParams.get("refreshToken");
            System.out.println("token xac thuc websocket: " + token);
            System.out.println("refreshToken xac thuc websocket: " + refreshToken);
            if (token != null && token.startsWith("token=")) {
                token = token.replace("token=", ""); // Lấy phần token từ query string
            }
            System.out.println("xác thực token: " + new CustomJWT().validateToken(token));
            try {
                if (new CustomJWT().validateToken(token) == 200) {
                    attributes.put("idUser", new UsernamePasswordAuthenticationToken(new CustomJWT().extractUserId(token), null, new ArrayList<>()));
                    return true;
                }
                //nếu token hợp lệ nhưng hết thời hạn thì kiểm tra refresh token xem có hợp lệ và còn thời hạn không
                else if (token != null && new CustomJWT().validateToken(token) == 200) {
                    if (new CustomJWT().validateToken(refreshToken) == 200) {
                        System.out.println("Vo duoc refresh tokennnnn");
                        response.setStatusCode(HttpStatus.OK); // Trả về mã lỗi 404
                        return false;
                    } else {
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return false;
                    }
                }

            } catch (Exception e) {
                System.out.println("Invalid JWT token: " + e.getMessage());
                return false; // Nếu token không hợp lệ, từ chối kết nối
            }
            return true;
        }

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
