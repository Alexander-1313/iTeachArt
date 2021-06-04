package com.iteachart.web.controller;

import com.iteachart.model.configuration.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CheckRoleController {

    private final JwtProvider jwtProvider;

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

    @GetMapping("/gentoken")
    public String genToken(String email){
        return jwtProvider.generateToken(email);
    }
}
