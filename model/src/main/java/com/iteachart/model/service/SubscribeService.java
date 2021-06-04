package com.iteachart.model.service;

import com.iteachart.model.entity.Subscribe;

public interface SubscribeService {

    boolean isValid(String email);

    void subscribeNotification(String email);

    Subscribe getSubscribeByType(String type);
}
