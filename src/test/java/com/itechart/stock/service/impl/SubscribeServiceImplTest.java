package com.itechart.stock.service.impl;

import com.itechart.stock.entity.User;
import com.itechart.stock.repository.UserRepository;
import com.itechart.stock.service.SubscribeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SubscribeServiceImplTest {

    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testValid(){
        User user = new User();
        user.setEmail("dqweqr@gmail.com");
        user.setSubscribeExpireDate(LocalDate.now().minusDays(2L));
        User saveUser = userRepository.save(user);

        boolean valid = subscribeService.isValid(saveUser.getEmail());

        assertTrue(valid);
    }

    @Test
    public void testValidFalse(){
        User user = new User();
        user.setEmail("ksdgoiwe@gmail.com");
        user.setSubscribeExpireDate(LocalDate.now().plusDays(2L));
        User saveUser = userRepository.save(user);

        boolean valid = subscribeService.isValid(saveUser.getEmail());

        assertFalse(valid);
    }
}