package com.milk.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-09 21:16
 */

@SpringBootApplication
// 开启事务
@EnableTransactionManagement
// 开启异步线程池任务
@EnableAsync
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class,args);
    }
}
