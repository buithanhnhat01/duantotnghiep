package com.fptpoly.main.Util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@EnableAsync
public class _MailService implements Mail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async // annotation luồng xử lý riêng biệt
    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("longzu102@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        System.out.println("Success");
        javaMailSender.send(message);
    }

    @Override
    @Async
    public void sendHtmlMail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(subject);
        helper.setFrom("longzu102@gmail.com");
        helper.setTo(to);
        boolean html = true;
        helper.setText("<b>THÔNG TIN ĐƠN HÀNG</b>,<br><i>"+body+"</i><a href='http://localhost:3363/home/dashboard?status=PACKING'> chi tiết</a><hr><label>Cảm Ơn Quý Khách Đã Mua Hàng</label>", html);
        javaMailSender.send(message);
    }



}
