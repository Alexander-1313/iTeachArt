package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Subscribe;
import com.itechart.stock.entity.User;
import com.itechart.stock.repository.SubscribeRepository;
import com.itechart.stock.repository.UserRepository;
import com.itechart.stock.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscribeRepository subscribeRepository;

    @Test
    public void testBlockUser() {
        User user = new User();
        user.setEmail("qwer@gmail.com");
        user.setIsBlocked(false);

        User saveUser = userRepository.save(user);
        userService.blockUser(saveUser.getEmail());

        assertTrue(userRepository.findByEmail(saveUser.getEmail()).getIsBlocked());
    }

    @Test
    public void testUnblockUser() {
        User user = new User();
        user.setEmail("qwer1@gmail.com");
        user.setIsBlocked(true);

        User saveUser = userRepository.save(user);
        userService.unblockUser(saveUser.getEmail());

        assertFalse(userRepository.findByEmail(saveUser.getEmail()).getIsBlocked());
    }

    @Test
    public void testChangeSubscribeStatus() {
        User user = new User();
        user.setEmail("qwer3@gmail.com");
        user.setSubscribeEnabled(true);

        User saveUser = userRepository.save(user);

        userService.changeSubscribeStatus(saveUser.getEmail(), false);

        assertFalse(userRepository.findById(saveUser.getId()).get().getSubscribeEnabled());
    }

    @Test
    public void testUpdateSubscribe() {
        final String subscribeType = "PREMIUM";
        User user = new User();
        user.setEmail("qwer2@gmail.com");
        user.setIsBlocked(true);

        Subscribe subscribe = subscribeRepository.findById(2L).get();
        Set<User> subscribeUser = subscribe.getSubscribeUser();
        subscribeUser.add(user);
        subscribe.setSubscribeUser(subscribeUser);

        User saveUser = userRepository.save(user);
        subscribeRepository.save(subscribe);

        userService.updateSubscribe(saveUser.getEmail(), subscribeType);

        assertTrue(userRepository.findByEmail(saveUser.getEmail()).getSubscribeExpireDate().isAfter(LocalDate.now()));
    }

}