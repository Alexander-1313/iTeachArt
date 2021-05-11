package com.iteachart.stock.service;

import com.iteachart.stock.entity.Subscribe;

public interface UserService {

    void blockUser(String email);

    void unblockUser(String email);

    void changeSubscribe(String email, Subscribe subscribe);

    void updateSubscribe(String email);
}
