package com.springboot.bookingcare.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisConnectionTest implements CommandLineRunner {
    private final RedisConnectionFactory redisConnectionFactory;

    public RedisConnectionTest(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    public void run(String... args) {
        try (RedisConnection connection = redisConnectionFactory.getConnection()) {
            System.out.println("✅ Kết nối Redis thành công: " + connection.ping());
        } catch (Exception e) {
            System.err.println("❌ Lỗi kết nối Redis: " + e.getMessage());
        }
    }
}
