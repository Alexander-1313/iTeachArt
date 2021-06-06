package com.itechart.stock.controller;

import com.itechart.stock.entity.User;
import com.itechart.stock.service.UserService;
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

    @PostMapping("/admin/block")
    public User blockUser(@RequestParam String user) {
        return userService.blockUser(user);
    }

    @PostMapping("/admin/unblock")
    public User unblockUser(@RequestParam String user) {
        return userService.unblockUser(user);
    }

    @PostMapping("/admin/changeSubscribe")
    public User changeSubscribeStatus(@RequestParam String user,
                                      @RequestParam Boolean status) {
        return userService.changeSubscribeStatus(user, status);
    }

    @PostMapping("/admin/updateSubscribe")
    public User updateSubscribe(@RequestParam String user, @RequestParam String subscribe) {
        return userService.updateSubscribe(user, subscribe);
    }

    @PostMapping("/admin/delete")
    public User deleteUser(@RequestParam String user){
        return userService.deleteUser(user);
    }
}
