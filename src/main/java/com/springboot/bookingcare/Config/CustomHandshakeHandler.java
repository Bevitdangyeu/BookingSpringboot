package com.springboot.bookingcare.Config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(org.springframework.http.server.ServerHttpRequest request,
                                      org.springframework.web.socket.WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return (authentication != null && authentication.isAuthenticated()) ? authentication : null;
    }
}
