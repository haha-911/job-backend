package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.pojo.LoginLog;
import com.milk.job.model.pojo.OperLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.LoginLogVo;

import java.util.List;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-10 22:23
 */

public interface OperLogService extends IService<OperLog>{


    Page<OperLog> getOperLog(LoginLogVo loginLogVo);

    void delOperlog(List<Integer> ids);


}
