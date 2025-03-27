package com.springboot.bookingcare.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // client đăng kí nhận tin nhắn bắt đầu từ /topic/**
        config.setApplicationDestinationPrefixes("/app"); // định tuyến khi client gửi tin nhắn đến cho server thì cũng phải bắt đầu từ /app/** ** này nằm trong api đ xử lý, nhưng bắt buộc phải bắt đầu từ app thì nó mới đnh tuyến tìm các nơi có anotation là @MessageMapping, app/send thì ở api sẽ là @MessageMapping("/send")
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // user đăng kí một endponit là /ws khi người dùng yêu cầu kết nối đến enpoint là chat thì giữa server và client sẽ nâng cấp kết nối thành websocket
        //client sẽ đăng kí nâng cấp websocket với endpoid /ws
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:4200") // Cho phép mọi nguồn
                .withSockJS()
                // .addInterceptors(new JwtAuthInterceptor()) // Thêm Interceptor xử lý JWT
            //    .setHandshakeHandler(new CustomHandshakeHandler())
                ;// Hỗ trợ fallback qua SockJS
                // Chỉ sử dụng transport nàu
        System.out.println("WebSocket endpoint registered: /ws");
    }
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new JwtAuthInterceptor());  // Add interceptor to handle inbound messages
//    }
}
