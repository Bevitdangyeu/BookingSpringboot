package com.springboot.bookingcare.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // Bước 1: tạo kết nối
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        // sử dụng LettuceConnectionFactor để tạo kết nối đến redis (Lettuce là thư viện kết nối redis hiệu quả)
        return new LettuceConnectionFactory();
    }

    // bước 2: Cấu hình thao tác ữ liệu

    // RedisTemplate là công cụ chính để thao tác dữ liệu trên redis
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
