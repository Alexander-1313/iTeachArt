package com.iteachart.stock.service;

import com.iteachart.stock.entity.User;
import com.iteachart.stock.feign.StockFeignClient;
import com.iteachart.stock.repository.UserRepository;
import com.iteachart.stock.util.StringConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final UserRepository userRepository;
    private final StockFeignClient stockFeignClient;
    private final MailService mailService;

    @Scheduled(fixedRate = 1000000)
    public void loadDataFromStock(){
        System.out.println(stockFeignClient.getCompanyShares("AAPL"));
    }

    @Scheduled(fixedRate = 1000000)
    public void notifyUserAboutSubscribe(){
        List<User> allUsers = userRepository.findAll();
        for(User user: allUsers){
            if(user.getSubscribeExpireDate().isAfter(LocalDate.now())){
                mailService.sendEmail(user.getEmail(), StringConstant.SUBSCRIBE_SUBJECT, StringConstant.ENDING_TEXT);
                user.setIsBlocked(true);
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
