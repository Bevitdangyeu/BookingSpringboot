package com.springboot.bookingcare.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="permission")
@Entity
public class PermissionEntity {
    @Id
    @Column(name="permissionId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
    @SequenceGenerator(name = "permission_seq", sequenceName = "permission_permissionId_seq", allocationSize = 1)
    private int permissionId;
    @Column(name="permissionCode")
    private String permissionCode;
    @Column(name="permissionName")
    private String permissionName;
    @ManyToMany(mappedBy = "permissions")
    private List<RoleEntity> roles=new ArrayList<RoleEntity>();
}
