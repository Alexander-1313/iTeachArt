package com.itechart.stock.service;

public interface MailService {

    void sendEmail(String email, String subject, String text);
}
