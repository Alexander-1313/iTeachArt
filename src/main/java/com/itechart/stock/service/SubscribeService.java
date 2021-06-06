package com.itechart.stock.service;

import com.itechart.stock.entity.Subscribe;

public interface SubscribeService {

    boolean isValid(String email);

    void subscribeNotification(String email);

    Subscribe getSubscribeByType(String type);
}
