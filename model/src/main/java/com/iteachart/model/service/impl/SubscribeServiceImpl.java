package com.iteachart.model.service.impl;

import com.iteachart.model.entity.Subscribe;
import com.iteachart.model.entity.User;
import com.iteachart.model.repository.SubscribeRepository;
import com.iteachart.model.repository.UserRepository;
import com.iteachart.model.service.MailService;
import com.iteachart.model.service.SubscribeService;
import com.iteachart.model.util.StringConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public boolean isValid(String email) {
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
            return false;
        }
        return userByEmail.getSubscribeExpireDate().isBefore(LocalDate.now());
    }

    @Override
    public void subscribeNotification(String email) {
        User userByEmail = userRepository.findByEmail(email);
        LocalDate subscribeExpireDate = userByEmail.getSubscribeExpireDate();
        if (subscribeExpireDate.minusDays(2).isBefore(LocalDate.now()) && subscribeExpireDate.isAfter(LocalDate.now().minusDays(2))) {
            mailService.sendEmail(email, StringConstant.SUBSCRIBE_SUBJECT, StringConstant.SOON_ENDING_TEXT);
            log.info("user subscribe with email={} is soon ending", email);
        } else if (subscribeExpireDate.isAfter(LocalDate.now())) {
            mailService.sendEmail(email, StringConstant.SUBSCRIBE_SUBJECT, StringConstant.ENDING_TEXT);
            userByEmail.setIsBlocked(true);
            userRepository.save(userByEmail);
            log.info("user subscribe with email={} is ended", email);
        } else {
            log.info("it's {} days to subscribe ending!", LocalDate.now().getDayOfYear() - subscribeExpireDate.getDayOfYear());
        }
    }

    @Override
    public Subscribe getSubscribeByType(String type) {
        return subscribeRepository.findByType(type);
    }
}
