package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Integer> {
    RoleEntity findByRoleCode(String roleCode);
}
