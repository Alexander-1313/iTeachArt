package com.iteachart.model.service.impl;

import com.iteachart.model.entity.User;
import com.iteachart.model.repository.UserRepository;
import com.iteachart.model.service.UserValidationService;
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
