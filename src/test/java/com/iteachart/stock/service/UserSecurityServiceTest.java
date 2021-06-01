package com.iteachart.stock.service;

import com.iteachart.stock.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSecurityServiceTest {

    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testLoadUser(){
        final String email = "alexander.rybak2020@mail.ru";
        UserDetails actual = userSecurityService.loadUserByUsername(email);
        User expected = new User(email, bCryptPasswordEncoder.encode("123456"), new HashSet<>(){
            {
                add(roleRepository.findById(2L).get());
            }
        });
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getAuthorities(), actual.getAuthorities());
    }
}