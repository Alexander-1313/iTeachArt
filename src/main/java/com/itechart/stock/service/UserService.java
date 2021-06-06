package com.itechart.stock.service;

import com.itechart.stock.entity.User;

public interface UserService {

    User blockUser(String email);

    User unblockUser(String email);

    User changeSubscribeStatus(String email, Boolean status);

    User updateSubscribe(String email, String subscribe);

    User deleteUser(String email);

    User saveUser(User user);
}
