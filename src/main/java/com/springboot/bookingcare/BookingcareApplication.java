package com.springboot.bookingcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.crypto.Mac;
import java.security.Provider;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.TimeZone;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class

BookingcareApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookingcareApplication.class, args);
	}
}
