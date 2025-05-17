package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.Config.CustomJWT;
import com.springboot.bookingcare.DTO.AuthenticationRequest;
import com.springboot.bookingcare.DTO.AuthenticationResponse;
import com.springboot.bookingcare.DTO.UserDTO;
import com.springboot.bookingcare.Service.UserService;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetailService;
import com.springboot.bookingcare.ServiceImplement.CustomeUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthenticateAPI {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    CustomeUserDetailService customeUserDetailService;
    @Autowired CustomJWT jwt;
    @Autowired
    UserService userService;
    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authentication) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authentication.getUsername(), authentication.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên đăng nhập hoặc mật khẩu không đúng");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản không tồn tại hoặc đã bị vô hiệu hóa");
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi trong quá trình đăng nhập");
        }

        System.out.println("Authentication successful for user: " + authentication.getUsername());
        UserDetails user = customeUserDetailService.loadUserByUsername(authentication.getUsername());
        UserDTO userDTO = userService.findByUserName(user.getUsername());
        AuthenticationResponse authenticationResponse = jwt.generateToken(user.getUsername(), userDTO.getIdUser(), userDTO.getRole());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(user.getAuthorities());
        authenticationResponse.setRole(determineRole(grantedAuthorityList));

        return ResponseEntity.ok(authenticationResponse);
    }
    private String determineRole(List<GrantedAuthority> authorities) {
        if (authorities.contains(new SimpleGrantedAuthority("CREATE")) &&
                authorities.contains(new SimpleGrantedAuthority("UPDATE")) &&
                authorities.contains(new SimpleGrantedAuthority("DELETE"))) {
            return "ADMIN";
        } else if (authorities.contains(new SimpleGrantedAuthority("CREATE")) &&
                authorities.contains(new SimpleGrantedAuthority("UPDATE"))) {
            return "DOCTOR";
        }
        return "USER";
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) throws Exception {
        // lấy refresh token từ header
        String refreshToken=request.getHeader("Authorization-Refresh");
        // kiểm tra refresh token có hợp lệ không
        if(jwt.validateToken(refreshToken)==200){
            AuthenticationResponse authenticationResponse=new AuthenticationResponse();
            authenticationResponse.setAccessToken(jwt.refreshToken(jwt.extractUserName(refreshToken),Integer.parseInt(jwt.extractUserId(refreshToken)),"admin"));
            authenticationResponse.setRefreshToken(refreshToken);
            return ResponseEntity.ok(authenticationResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Trả về 500 nếu có lỗi hệ thống
        }
    }
    @GetMapping("/getIdUser")
    public ResponseEntity<Integer> getUser(){
        // lấy thông tin user từ Spring security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            UserDetails userDetail=(UserDetails) authentication.getPrincipal();
            // ép kiểu về kiểu CustomUserDetail
            CustomeUserDetails customeUserDetails=(CustomeUserDetails) userDetail;
            Integer idUser=customeUserDetails.getUser().getIdUser();
            return ResponseEntity.ok(idUser);
        }
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
