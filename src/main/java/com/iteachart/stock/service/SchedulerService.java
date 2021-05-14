package com.iteachart.stock.service;

import com.iteachart.stock.entity.User;
import com.iteachart.stock.feign.StockFeignClient;
import com.iteachart.stock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SchedulerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockFeignClient stockFeignClient;

    @Scheduled(fixedRate = 10000)
    public void loadDataFromStock(){
        System.out.println(stockFeignClient.getCompanyShares("AAPL"));
    }

    @Scheduled(fixedRate = 10000)
    public void notifyUserAboutSubscribe(){
        List<User> allUsers = userRepository.findAll();
        for(User user: allUsers){
//            if(user.getSubscribeExpireDate().isAfter(LocalDate.now())){
//                System.out.println("notify user about his end subscribe...");
//                user.setIsBlocked(true);
//            }else if(LocalDate.now().minusDays(2L).isAfter(user.getSubscribeExpireDate())){
//                System.out.println("notify user about soon ending of subscribe...");
//            }else{
//                System.out.println("user with email = " + user.getEmail()+ " is subscribed...");
//            }
            System.out.println("user = " + user);
        }

    }

}
