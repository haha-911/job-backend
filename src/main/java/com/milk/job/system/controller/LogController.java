package com.milk.job.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.log.Log;
import com.milk.job.common.log.OperType;
import com.milk.job.model.pojo.LoginLog;
import com.milk.job.model.pojo.OperLog;
import com.milk.job.model.vo.LoginLogVo;
import com.milk.job.system.service.LoginLogService;
import com.milk.job.system.service.OperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-20 17:11
 */

@Slf4j
@RestController
@Api(value = "日志",tags = "log")
@RequestMapping("/job/log")
public class LogController {

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private OperLogService operLogService;

    @PostMapping("/login")
    @ApiOperation("登录日志")
    public R getLoginLog(@RequestBody LoginLogVo loginLogVo){

        Page<LoginLog> page = loginLogService.getLoginLog(loginLogVo);

        return R.success(page);

    }

    @PostMapping("/oper")
    @ApiOperation("操作日志")
    public R getOperLog(@RequestBody LoginLogVo loginLogVo){

        Page<OperLog> page = operLogService.getOperLog(loginLogVo);

        return R.success(page);

    }

    @DeleteMapping("/del/oper")
    @ApiOperation("删除操作日志")
    @Log(title = "删除操作日志",businessType = OperType.DELETE)
    public R delOperLog(@RequestBody List<Integer> ids){

         operLogService.delOperlog(ids);

        return R.success("删除成功！");

    }

    @DeleteMapping("/del/login")
    @ApiOperation("删除登录日志")
    @Log(title = "删除登录日志",businessType = OperType.DELETE)
    public R delLoginLog(@RequestBody List<Integer> ids){

        loginLogService.delLoginlog(ids);

        return R.success("删除成功！");

    }
}
