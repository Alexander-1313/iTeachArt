package com.iteachart.model.service;

import com.iteachart.model.entity.User;

public interface UserService {

    User blockUser(String email);

    User unblockUser(String email);

    User changeSubscribeStatus(String email, Boolean status);

    User updateSubscribe(String email);
}
