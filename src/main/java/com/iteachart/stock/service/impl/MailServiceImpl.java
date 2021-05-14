package com.iteachart.stock.service.impl;

import com.iteachart.stock.service.MailService;
import com.iteachart.stock.util.StringConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Override
    public void sendEmail(String email, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host" , "smtp.mail.ru");
        props.put("mail.stmp.user" , "username");

        //To use TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.password", "password");
        //To use SSL
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                StringConstant.EMAIL_NAME, StringConstant.EMAIL_PASSWORD);
                    }
                });
        String to = email;
        String from = StringConstant.EMAIL_NAME;
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(text);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.mail.ru" , 465 , "username", "password");
            transport.send(msg);
            log.info("message was sent to={}", email);
        }
        catch(Exception exc) {
            log.error(exc.getMessage());
        }
    }
}
