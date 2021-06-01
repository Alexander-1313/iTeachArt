package com.iteachart.stock.service;

import com.iteachart.stock.entity.Subscribe;
import com.iteachart.stock.entity.User;

public interface UserService {

    User blockUser(String email);

    User unblockUser(String email);

    User changeSubscribe(String email, Subscribe subscribe);

    User updateSubscribe(String email);
}
