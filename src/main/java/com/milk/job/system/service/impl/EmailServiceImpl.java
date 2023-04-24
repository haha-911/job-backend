package com.milk.job.system.service.impl;

import com.milk.job.common.utils.VerifyCodeUtils;
import com.milk.job.system.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-04-24 17:39
 */

@Service
@Transactional
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender javaMailSender;



    @Override
    public boolean sendMsg(String to, String text,String title) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            设置发件人
            mimeMessageHelper.setFrom(from);
//            设置收件人
            mimeMessageHelper.setTo(to);
//            设置邮件主题
            mimeMessageHelper.setSubject(title);
//            设置验证码的样式
            mimeMessageHelper.setText(text,true);
            javaMailSender.send(mimeMessage);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;

    }

}
