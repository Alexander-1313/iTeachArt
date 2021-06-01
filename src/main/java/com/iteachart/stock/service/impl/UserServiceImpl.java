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
    public User changeSubscribe(String email, Subscribe subscribe) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
            return null;
        }else{
            userByEmail.setSubscribe(subscribe);
            userByEmail.setSubscribeExpireDate(LocalDate.now().plusDays(UserUtils.subscribeDuration));

            Subscribe subscribeFromRepo = subscribeRepository.findById(subscribe.getId()).get();
            Set<User> subscribeUser = subscribeFromRepo.getSubscribeUser();
            subscribeUser.add(userByEmail);
            subscribeFromRepo.setSubscribeUser(subscribeUser);
            User save = userRepository.save(userByEmail);
            subscribeRepository.save(subscribeFromRepo);

            log.info("subscribe was changed!");
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
