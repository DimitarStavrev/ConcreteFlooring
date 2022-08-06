package com.example.theproject.service.impl;

import com.example.theproject.model.entity.Role;
import com.example.theproject.model.entity.User;
import com.example.theproject.model.entity.enums.RoleNameEnums;
import com.example.theproject.model.user.ProjetUserDetails;
import com.example.theproject.repository.UserRepository;
import com.example.theproject.service.impl.impl.ProjectUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProjectUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private User testUser;
    private Role adminRole, userRole;

    private ProjectUserDetailsService serviceToTest;

    @BeforeEach
    void init() {
        serviceToTest = new ProjectUserDetailsService(mockUserRepo);
        testUser = new User();

        adminRole = new Role();
        adminRole.setRole(RoleNameEnums.ADMINISTRATOR);
        userRole = new Role();
        userRole.setRole(RoleNameEnums.USER);

        testUser.setUsername("mitko");
        testUser.setPassword("123");
        testUser.setFullName("Dimitar Stavrev");
        testUser.setEmail("admin@admin.com");
        testUser.setAge(33);
        testUser.setRoles(List.of(adminRole, userRole));
    }

    @Test
    void testLoadUserByUsername_UsernameExists() {

        when(mockUserRepo.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));


        ProjetUserDetails projetUserDetails = (ProjetUserDetails)
                serviceToTest.loadUserByUsername(testUser.getUsername());

        Assertions.assertEquals(testUser.getUsername(), projetUserDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), projetUserDetails.getPassword());
        Assertions.assertEquals(testUser.getFullName(), projetUserDetails.getFullName());

        Collection<? extends GrantedAuthority> authorities = projetUserDetails.getAuthorities();

        Assertions.assertEquals(2, authorities.size());

        Iterator<? extends GrantedAuthority> authoritiesIterator = authorities.iterator();

        Assertions.assertEquals("ROLE_" + RoleNameEnums.ADMINISTRATOR.name(),
                authoritiesIterator.next().getAuthority());
        Assertions.assertEquals("ROLE_" + RoleNameEnums.USER.name(),
                authoritiesIterator.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExists() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("NotFoundUser"));
    }
}
