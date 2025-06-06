//package com.springboot.bookingcare.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Cho phép tất cả API
//                        .allowedOrigins("http://localhost:4200") // Cho phép Angular truy cập
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các method được phép
//                        .allowedHeaders("*")
//                        .allowCredentials(true);
//            }
//        };
//    }
//}
