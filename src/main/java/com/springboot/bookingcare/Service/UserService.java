package com.springboot.bookingcare.Service;

import com.springboot.bookingcare.DTO.UserDTO;

public interface UserService {
    UserDTO findByUserName(String userName);
    UserDTO addUser(UserDTO userDTO);
}
