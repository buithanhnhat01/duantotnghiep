package com.fptpoly.main.Util;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.mail.MessagingException;
import javax.naming.Context;


public interface Mail {

    void sendEmail(String toEmail, String subject, String body);

    void sendHtmlMail(String to, String subject, String body) throws MessagingException;
}
