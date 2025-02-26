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

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomeUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByUserName(username);
        if(userEntity==null){
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> grantedAuthorityList=new ArrayList<>();
        if(userEntity.getRole()!=null){
            for(PermissionEntity permisstion:userEntity.getRole().getPermissions()){
                grantedAuthorityList.add(new SimpleGrantedAuthority(permisstion.getPermissionCode()));
            }
        }
        // trả về một UserDetial( ở đây trả về một UserDetailCustom)
        CustomeUserDetails userDetails=new CustomeUserDetails(userEntity,grantedAuthorityList);
        return userDetails;
    }
}
