package com.iteachart.web.controller;

import com.iteachart.model.entity.User;
import com.iteachart.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;

    @PostMapping("/block")
    public User blockUser(@RequestParam("email") String email){
        return userService.blockUser(email);
    }

    @PostMapping("/unblock")
    public User unblockUser(@RequestParam("email") String email){
        return userService.unblockUser(email);
    }

    @PostMapping("/changeSubscribe")
    public User changeSubscribeStatus(@RequestParam("email") String email,
                                @RequestParam("status") Boolean status){
        return userService.changeSubscribeStatus(email, status);
    }

    @PostMapping("/updateSubscribe")
    public User updateSubscribe(@RequestParam("email") String email){
        return userService.updateSubscribe(email);
    }
}
