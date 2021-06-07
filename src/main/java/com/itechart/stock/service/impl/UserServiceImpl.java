package com.itechart.stock.service.impl;

import com.itechart.stock.entity.Company;
import com.itechart.stock.entity.Role;
import com.itechart.stock.entity.Subscribe;
import com.itechart.stock.entity.User;
import com.itechart.stock.repository.CompanyRepository;
import com.itechart.stock.repository.RoleRepository;
import com.itechart.stock.repository.SubscribeRepository;
import com.itechart.stock.repository.UserRepository;
import com.itechart.stock.service.UserService;
import com.itechart.stock.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CompanyRepository companyRepository;

    @Override
    public User blockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if (byEmail == null) {
            log.info("user with email={} not found", email);
            return null;
        }
        byEmail.setIsBlocked(true);
        User save = userRepository.save(byEmail);
        log.info("user with email={} was blocked", email);
        return save;
    }

    @Override
    public User unblockUser(String email) {
        User byEmail = userRepository.findByEmail(email);
        if (byEmail == null) {
            log.info("user with email={} not found", email);
            return null;
        }
        byEmail.setIsBlocked(false);
        User save = userRepository.save(byEmail);
        log.info("user with email={} was unblocked", email);
        return save;
    }

    @Override
    public User changeSubscribeStatus(String email, Boolean status) {
        System.out.println("email = " + email);
        System.out.println("status = " + status);
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
            return null;
        } else {
            userByEmail.setSubscribeEnabled(status);
            User save = userRepository.save(userByEmail);
            log.info("subscribe status was changed!");
            return save;
        }
    }

    @Override
    public User updateSubscribe(String email, String subscribe) {
        User userByEmail = userRepository.findByEmail(email);
        Subscribe subscribeByType = subscribeRepository.findByType(subscribe);
        if(subscribeByType == null){
            log.info("no such subscribe with name={}", subscribe);
            return null;
        }
        if (userByEmail == null) {
            log.info("user with email={} not found", email);
            return null;
        } else {
            userByEmail.setSubscribe(subscribeByType);
            userByEmail.setSubscribeEnabled(true);
            userByEmail.setSubscribeExpireDate(LocalDate.now().plusDays(UserUtils.subscribeDuration));
            User save = userRepository.save(userByEmail);
            log.info("subscribe was updated!");
            return save;
        }
    }

    @Override
    public User deleteUser(String email) {
        User userByEmail = userRepository.findByEmail(email);
        if(userByEmail == null){
            log.info("there is no user with email={}", email);
            return null;
        }
        userRepository.delete(userByEmail);
        Role role = roleRepository.findById(userByEmail.getRole().getId()).get();
        role.getRoleUser().removeIf(u -> u.getEmail().equals(email));
        roleRepository.save(role);
        for(Company company: userByEmail.getCompanies()){
            Company companyByTicker = companyRepository.findByTicker(company.getTicker());
            companyByTicker.getUsers().removeIf(u -> u.getEmail().equals(email));
            companyRepository.save(companyByTicker);
        }
        return userByEmail;
    }

    @Override
    public User saveUser(User user) {
        user.setIsBlocked(false);
        user.setSubscribeEnabled(false);
        user.setCreatedAt(new Date());
        user.setRole(roleRepository.findById(1L).get());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
