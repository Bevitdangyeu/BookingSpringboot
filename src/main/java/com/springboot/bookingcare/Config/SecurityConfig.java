package com.springboot.bookingcare.Config;

import com.springboot.bookingcare.ServiceImplement.CustomeUserDetailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.Set;
import java.util.stream.Collectors;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomeUserDetailService customeUserDetailService;
    @Autowired JwtRequestFilter jwtRequestFilter;
    @Autowired CustomeAccessDeniedHandler customeAccessDeniedHandler;
    public void init() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return customeUserDetailService;
    }
    //hàm này sẽ được sử dụng để mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // cấu hình chuyển hướng khi truy cập vào tài nguyên không có quyền
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return customeAccessDeniedHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Tắt CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/authenticate","/public/**","/uploads/**","/ws/**","/chat/**","/queue/errors").permitAll()// Cho phép truy cập endpoint này
                        // nếu có được 4 quyền READ CREATE UPDATE DELETE thì mới có thể truy cập được admin 1
                        .requestMatchers("/admin1").access((authentication, context) -> {
                            authentication.get().getAuthorities().forEach(authority -> {
                                System.out.println("Granted Authority: " + authority.getAuthority());
                            });
                            boolean hasAllAuthorities = authentication.get().getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toSet())
                                    .containsAll(Set.of("READ", "CREATE", "UPDATE", "DELETE"));
                            return new AuthorizationDecision(hasAllAuthorities);
                        })
                        // nếu có được 3 quyền READ CREATE UPDATE  thì mới có thể truy cập được doctor
                        .requestMatchers("/doctor").access((authentication, context) -> {
                            authentication.get().getAuthorities().forEach(authority -> {
                                System.out.println("Granted Authority: " + authority.getAuthority());
                            });
                            boolean hasAllAuthorities = authentication.get().getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toSet())
                                    .containsAll(Set.of("READ", "CREATE", "UPDATE"));
                            return new AuthorizationDecision(hasAllAuthorities);
                        })
                        .requestMatchers("/admin2").hasAnyAuthority("CREATE")
                        // .requestMatchers("/user/**").hasAnyAuthority("READ")
                        .anyRequest().authenticated() // Các request khác cần xác thực
                )
                // cài đặt đi qua jwt trước
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Đảm bảo không sử dụng session server-side
                );
                return http.build();
    }
    @SuppressWarnings("removal")
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
    // cấu hình bỏ qua những tài nguyên tĩnh
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**","/js/**","/vendors/**","/assets/**","/admin/**","/profile/**","/uploads/**","/image/**","/chatcss/**","/post/**"); // Bỏ qua các yêu cầu đến các tài nguyên tĩnh

    }

}
