package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Subscribe;
import com.iteachart.stock.entity.User;
import com.iteachart.stock.repository.SubscribeRepository;
import com.iteachart.stock.repository.UserRepository;
import com.iteachart.stock.service.UserService;
import com.iteachart.stock.util.UserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private SubscribeRepository subscribeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SubscribeRepository subscribeRepository) {
        this.userRepository = userRepository;
        this.subscribeRepository = subscribeRepository;
    }

    @Override
    public void blockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail != null){
            byEmail.setIsBlocked(true);
            userRepository.save(byEmail);
            LOGGER.info("user with email = " + email + " was blocked");
        }else{
            LOGGER.info("user with email = " + email + " not found");
        }
    }

    @Override
    public void unblockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail != null){
            byEmail.setIsBlocked(false);
            userRepository.save(byEmail);
            LOGGER.info("user with email = " + email + " was unblocked");
        }else{
            LOGGER.info("user with email = " + email + " not found");
        }
    }

    @Override
    public void changeSubscribe(String email, Subscribe subscribe) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            LOGGER.info("user with email = " + email + " not found");
        }else{
            userByEmail.setSubscribe(subscribe);
            userByEmail.setSubscribeExpireDate(LocalDate.now().plusDays(UserUtils.subscribeDuration));

            Subscribe subscribeFromRepo = subscribeRepository.findById(subscribe.getId()).get();
            Set<User> subscribeUser = subscribeFromRepo.getSubscribeUser();
            subscribeUser.add(userByEmail);
            subscribeFromRepo.setSubscribeUser(subscribeUser);
            userRepository.save(userByEmail);
            subscribeRepository.save(subscribeFromRepo);

            LOGGER.info("subscribe was changed!");
        }
    }

    @Override
    public void updateSubscribe(String email) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            LOGGER.info("user with email = " + email + " not found");
        }else{
            userByEmail.setSubscribeExpireDate(LocalDate.now().plusDays(UserUtils.subscribeDuration));
            userRepository.save(userByEmail);
            LOGGER.info("subscribe was updated!");
        }
    }

}
