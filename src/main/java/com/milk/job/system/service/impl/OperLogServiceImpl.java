package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.pojo.LoginLog;
import com.milk.job.model.vo.LoginLogVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.invoke.LambdaConversionException;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.model.pojo.OperLog;
import com.milk.job.system.mapper.OperLogMapper;
import com.milk.job.system.service.OperLogService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-10 22:23
 */

@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLog> implements OperLogService{

    @Resource
    private OperLogMapper operLogMapper;

    @Override
    public Page<OperLog> getOperLog(LoginLogVo loginLogVo) {

        Page<OperLog> page = new Page<>(loginLogVo.getPage(), loginLogVo.getPageSize());

        LambdaQueryWrapper<OperLog> queryWrapper = new LambdaQueryWrapper<>();

        if (loginLogVo.getState()==null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        queryWrapper.eq(OperLog::getStatus,loginLogVo.getState());

        return  this.page(page, queryWrapper);


    }

    @Override
    public void delOperlog(List<Integer> ids) {
        operLogMapper.delOperlog(ids);
    }
}
