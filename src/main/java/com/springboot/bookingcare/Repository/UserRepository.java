package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    @Query("select u from UserEntity u where u.role.roleId=3")
    List<UserEntity> findByActiveTrue();
    UserEntity findByUserNameAndActiveTrue(String userName);
    UserEntity findByIdUser(int idUser);
}
