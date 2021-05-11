package com.iteachart.stock.repository;

import com.iteachart.stock.entity.Role;
import com.iteachart.stock.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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

    @Test
    public void testDeleteUserWithRole(){
        User user = new User();
        user.setEmail("alexanddedr");

        Role role = new Role();
        role.setRole("ROLE_US");

        List<User> roleUser = role.getRoleUser();
        roleUser.add(user);
        role.setRoleUser(roleUser);

        roleRepository.save(role);

        user.setRole(role);

        User save = userRepository.save(user);
        roleRepository.delete(role);

        assertNotNull(userRepository.getOne(save.getId()));
    }
}