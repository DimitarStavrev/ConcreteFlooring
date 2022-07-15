package com.example.theproject.model.entity;

import com.example.theproject.model.entity.enums.RoleNameEnums;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    private RoleNameEnums role;


    public Role() {
    }

    @Enumerated(EnumType.STRING)
    public RoleNameEnums getRole() {
        return role;
    }

    public void setRole(RoleNameEnums role) {
        this.role = role;
    }


}
