package com.iteachart.model.service;

import com.iteachart.model.entity.User;
import com.iteachart.model.repository.UserRepository;
import com.iteachart.model.util.StringConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Scheduled(cron = "0 0 0 * * *")
    public void notifyUserAboutSubscribe(){
        List<User> allUsers = userRepository.findAll();
        for(User user: allUsers){
            if(user.getSubscribeExpireDate().isAfter(LocalDate.now())){
                mailService.sendEmail(user.getEmail(), StringConstant.SUBSCRIBE_SUBJECT, StringConstant.ENDING_TEXT);
                user.setSubscribeEnabled(false);
                log.info("notify user with email={} about his end subscribe", user.getEmail());
            }else if(LocalDate.now().minusDays(2L).isAfter(user.getSubscribeExpireDate())){
                mailService.sendEmail(user.getEmail(), StringConstant.SUBSCRIBE_SUBJECT, StringConstant.ENDING_TEXT);
                log.info("notify user with={} about soon ending of subscribe", user.getEmail());
            }else{
                log.info("user with email={} is subscribed", user.getEmail());
            }
        }

    }

}
