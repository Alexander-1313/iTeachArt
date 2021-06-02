package com.iteachart.model.service;

import com.iteachart.model.entity.Subscribe;
import com.iteachart.model.entity.User;

public interface UserService {

    User blockUser(String email);

    User unblockUser(String email);

    User changeSubscribe(String email, Subscribe subscribe);

    User updateSubscribe(String email);
}
