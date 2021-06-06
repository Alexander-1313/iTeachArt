package com.itechart.stock.service.impl;

import com.itechart.stock.entity.User;
import com.itechart.stock.repository.UserRepository;
import com.itechart.stock.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    @Override
    public boolean isBlocked(String email) {
        User user = userRepository.findByEmail(email);
        return user.getIsBlocked();
    }
}
