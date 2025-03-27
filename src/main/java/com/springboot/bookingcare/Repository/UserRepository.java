package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByUserName(String userName);
    UserEntity findByIdUser(int idUser);
}
