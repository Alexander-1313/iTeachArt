package com.iteachart.stock.service;

import com.iteachart.stock.entity.Subscribe;

public interface SubscribeService {

    boolean isValid(String email);

    void subscribeNotification(String email);

    Subscribe getSubscribeByType(String type);
}
