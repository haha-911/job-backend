package com.milk.job.common.asynctask;

import com.milk.job.model.pojo.Notify;
import com.milk.job.system.service.EmailService;
import com.milk.job.system.service.NotifyService;
import com.milk.job.system.service.PositionService;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-09 17:16
 */
@Component
public class ThreadService {



    @Async("taskExecutor")
    public void addNotify(NotifyService notifyService, Notify notify){

        notifyService.save(notify);

    }


    @Async("taskExecutor")
    public void sendCode(EmailService emailService,String to,String text,String title){

        emailService.sendMsg(to,text,title);

    }

    @Async("taskExecutor")
    public void addPositionView(PositionService positionService,Integer id){

        positionService.addViews(id);

    }
}
