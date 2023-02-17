package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.pojo.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.LoginLogVo;

import java.util.List;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-12 18:19
 */

public interface LoginLogService extends IService<LoginLog>{


    Page<LoginLog> getLoginLog(LoginLogVo loginLogVo);

    LoginLog getPrevLogin(String  name);

    void delLoginlog(List<Integer> ids);
}
