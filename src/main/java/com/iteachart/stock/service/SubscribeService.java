package com.iteachart.stock.service;

public interface SubscribeService {

    boolean isValid(String email);

    void subscribeNotification(String email);
}
