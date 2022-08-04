package com.example.theproject.repository;

import com.example.theproject.model.entity.Role;
import com.example.theproject.model.entity.enums.RoleNameEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleNameEnums user);

}
