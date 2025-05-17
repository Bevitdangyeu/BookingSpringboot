package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.Entity.PermissionEntity;
import com.springboot.bookingcare.Entity.UserEntity;
import com.springboot.bookingcare.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
@Service
public class CustomeUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomeUserDetailService.class);
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user: {}", username);
        UserEntity userEntity = userRepository.findByUserNameAndActiveTrue(username);
        if (userEntity == null) {
            logger.warn("User not found or inactive: {}", username);
            //return new CustomeUserDetails(null,null);
           throw new UsernameNotFoundException("User not found");
        }
        logger.info("User found: {}", username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (userEntity.getRole() != null) {
            for (PermissionEntity permission : userEntity.getRole().getPermissions()) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(permission.getPermissionCode()));
            }
        }
        return new CustomeUserDetails(userEntity, grantedAuthorityList);
    }
}
