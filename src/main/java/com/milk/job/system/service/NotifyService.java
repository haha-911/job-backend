package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.NotifyDto;
import com.milk.job.model.pojo.Notify;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.NotifyVo;

import java.util.List;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

public interface NotifyService extends IService<Notify>{

    Page<NotifyDto> getPage(NotifyVo notifyVo);

    void delNotify(List<Integer> ids);

    void updateNotifyIsRead(List<Integer> ids);

}
