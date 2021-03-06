package com.itechart.stock.repository;

import com.itechart.stock.entity.Role;
import com.itechart.stock.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setEmail("riwoeur@mail.ru");

        User actual = userRepository.save(user);
        assertNotNull(actual);
    }

    @Test
    public void testGetUser(){
        User user = new User();
        user.setEmail("alexander");
        User save = userRepository.save(user);
        User one = userRepository.getOne(save.getId());
        assertNotNull(one);
        assertEquals(one.getId(), save.getId());
    }

    @Test
    public void testDeleteUser(){
        User deleteUser = new User();

        User save = userRepository.save(deleteUser);
        userRepository.delete(deleteUser);

        boolean existsById = userRepository.existsById(save.getId());
        assertFalse(existsById);
    }
}