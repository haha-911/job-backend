package com.milk.job.system.service;

import com.milk.job.system.service.impl.EmailServiceImpl;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-04-24 17:43
 */
public interface EmailService  {

    boolean sendMsg(String to, String text,String title);
}
