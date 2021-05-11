package com.iteachart.stock.service.impl;

import com.iteachart.stock.entity.Subscribe;
import com.iteachart.stock.entity.User;
import com.iteachart.stock.repository.SubscribeRepository;
import com.iteachart.stock.repository.UserRepository;
import com.iteachart.stock.service.UserService;
import com.iteachart.stock.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    @Override
    public void blockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail == null){
            log.info("user with email={} not found", email);
            return;
        }
        byEmail.setIsBlocked(true);
        userRepository.save(byEmail);
        log.info("user with email={} was blocked", email);
    }

    @Override
    public void unblockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail == null){
            log.info("user with email={} not found", email);
            return;
        }
        byEmail.setIsBlocked(false);
        userRepository.save(byEmail);
        log.info("user with email={} was unblocked", email);
    }

    @Override
    public void changeSubscribe(String email, Subscribe subscribe) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
        }else{
            userByEmail.setSubscribe(subscribe);
            userByEmail.setSubscribeExpireDate(LocalDate.now().plusDays(UserUtils.subscribeDuration));

            Subscribe subscribeFromRepo = subscribeRepository.findById(subscribe.getId()).get();
            Set<User> subscribeUser = subscribeFromRepo.getSubscribeUser();
            subscribeUser.add(userByEmail);
            subscribeFromRepo.setSubscribeUser(subscribeUser);
            userRepository.save(userByEmail);
            subscribeRepository.save(subscribeFromRepo);

            log.info("subscribe was changed!");
        }
    }

    @Override
    public void updateSubscribe(String email) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
        }else{
            userByEmail.setSubscribeExpireDate(LocalDate.now().plusDays(UserUtils.subscribeDuration));
            userRepository.save(userByEmail);
            log.info("subscribe was updated!");
        }
    }

}
