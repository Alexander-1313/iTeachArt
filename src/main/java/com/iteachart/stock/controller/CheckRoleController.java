package com.iteachart.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class CheckRoleController {

    @GetMapping("/user")
    public String user(Principal principal){
        log.info("user with email={}", principal.getName());
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Principal principal){
        log.info("user with email={}", principal.getName());
        return "admin!";
    }
}
