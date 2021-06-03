package com.iteachart.model.service.impl;

import com.iteachart.model.entity.User;
import com.iteachart.model.repository.SubscribeRepository;
import com.iteachart.model.repository.UserRepository;
import com.iteachart.model.service.UserService;
import com.iteachart.model.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    @Override
    public User blockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail == null){
            log.info("user with email={} not found", email);
            return null;
        }
        byEmail.setIsBlocked(true);
        User save = userRepository.save(byEmail);
        log.info("user with email={} was blocked", email);
        return save;
    }

    @Override
    public User unblockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail == null){
            log.info("user with email={} not found", email);
            return null;
        }
        byEmail.setIsBlocked(false);
        User save = userRepository.save(byEmail);
        log.info("user with email={} was unblocked", email);
        return save;
    }

    @Override
    public User changeSubscribeStatus(String email, Boolean status) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
            return null;
        }else{
            userByEmail.setSubscribeEnabled(status);
            User save = userRepository.save(userByEmail);
            log.info("subscribe status was changed!");
            return save;
        }
    }

    @Override
    public User updateSubscribe(String email) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
            return null;
        }else{
            userByEmail.setSubscribeExpireDate(LocalDate.now().plusDays(UserUtils.subscribeDuration));
            User save = userRepository.save(userByEmail);
            log.info("subscribe was updated!");
            return save;
        }
    }

}
