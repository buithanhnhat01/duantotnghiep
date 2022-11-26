package com.fptpoly.main.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class _MailService {

    @Autowired
    JavaMailSender sender;

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("longzu102@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        System.out.println("Success");
        sender.send(message);
    }
}
