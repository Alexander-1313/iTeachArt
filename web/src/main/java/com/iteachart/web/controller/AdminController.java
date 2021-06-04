package com.iteachart.web.controller;

import com.iteachart.model.entity.User;
import com.iteachart.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;

    @PostMapping("/block")
    public User blockUser(@RequestParam String user){
        return userService.blockUser(user);
    }

    @PostMapping("/unblock")
    public User unblockUser(@RequestParam String user){
        return userService.unblockUser(user);
    }

    @PostMapping("/admin/changeSubscribe")
    public User changeSubscribeStatus(@RequestParam String user,
                                      @RequestParam Boolean status){
        return userService.changeSubscribeStatus(user, status);
    }

    @PostMapping("/updateSubscribe")
    public User updateSubscribe(@RequestParam String user){
        return userService.updateSubscribe(user);
    }
}
