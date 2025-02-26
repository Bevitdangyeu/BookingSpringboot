package com.springboot.bookingcare.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name= "role")
@Entity
public class RoleEntity {
    @Id
    @Column(name="roleId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_roleId_seq", allocationSize = 1)
    private int roleId;
    @Column(name="roleCode")
    private String roleCode;
    @Column(name="roleName")
    private String roleName;
    @OneToMany(mappedBy="role")
    private List<UserEntity> user=new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="role_permission",
            joinColumns = @JoinColumn(name="roleId"),
            inverseJoinColumns = @JoinColumn(name="permissionId")
    )
    private List<PermissionEntity> permissions=new ArrayList<PermissionEntity>();
}
