package com.iteachart.web.controller;

import com.iteachart.model.entity.Subscribe;
import com.iteachart.model.entity.User;
import com.iteachart.model.service.SubscribeService;
import com.iteachart.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @PostMapping("/block")
    public User blockUser(@RequestParam("email") String email){
        return userService.blockUser(email);
    }

    @PostMapping("/unblock")
    public User unblockUser(@RequestParam("email") String email){
        return userService.unblockUser(email);
    }

    @PostMapping("/changeSubscribe")
    public User changeSubscribe(@RequestParam("email") String email,
                          @RequestParam("subscribe") String subscribe){
        Subscribe subscribeByType = subscribeService.getSubscribeByType(subscribe);
        return userService.changeSubscribe(email, subscribeByType);
    }

    @PostMapping("/updateSubscribe")
    public User updateSubscribe(@RequestParam("email") String email){
        return userService.updateSubscribe(email);
    }
}
