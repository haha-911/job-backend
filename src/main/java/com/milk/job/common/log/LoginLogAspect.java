package com.milk.job.common.log;

import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.common.exceptions.MyExceptionHandler;
import com.milk.job.common.utils.IPUtils;
import com.milk.job.common.utils.RequestUtils;
import com.milk.job.model.pojo.LoginLog;
import com.milk.job.model.pojo.User;
import com.milk.job.system.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-17 16:11
 */

@Aspect
@Component
@Slf4j
public class LoginLogAspect {

    @Resource
    private LoginLogService loginLogService;

    @Pointcut("execution(* com.milk.job.system.controller.LoginController.login(..))" )
    public void pointAdmin(){}

    @Pointcut("execution(* com.milk.job.system.controller.LoginController.userLogin(..))" )
    public void pointUser(){}


    @Around("pointAdmin()")
    public Object adminLog(ProceedingJoinPoint joinPoint) {

        LoginLog loginLog = this.setInfo();

        Object[] args = joinPoint.getArgs();

        User user = (User)args[0];

        loginLog.setUsername(user.getUsername());

        return this.handleException(loginLog,joinPoint);

    }

    @Around("pointUser()")
    public Object userLog(ProceedingJoinPoint joinPoint) {

        LoginLog loginLog = this.setInfo();

        Object[] args = joinPoint.getArgs();

        String username = (String)args[0];

        loginLog.setUsername(username);

        return this.handleException(loginLog,joinPoint);

    }

    private LoginLog setInfo(){
        LoginLog loginLog = new LoginLog();

        HttpServletRequest request = RequestUtils.getRequest();

        String Ip = IPUtils.getIpAddress(request);

        loginLog.setIp(Ip);

        LoginLog ipInfo = IPUtils.getIpInfo(Ip);

//        设置基本信息
        if (ipInfo!=null){
            loginLog.setIp(ipInfo.getIp());
            loginLog.setArea(ipInfo.getArea());
            loginLog.setCity(ipInfo.getCity());
            loginLog.setCountry(ipInfo.getCountry());
            loginLog.setDistrict(ipInfo.getDistrict());
            loginLog.setRegion(ipInfo.getRegion());
            loginLog.setIsp(ipInfo.getIsp());
        }

        return loginLog;

    }

    private R handleException(LoginLog loginLog,ProceedingJoinPoint joinPoint){
        try {
            R r = (R)joinPoint.proceed();
            loginLog.setMsg(r.getMsg());
            loginLog.setStatus(1);
            loginLogService.save(loginLog);
            log.info("Proceed:{}",r.getMsg());
            log.info("ipInfo:{}",loginLog);
            return r;
        } catch (CustomerException e){
            loginLog.setStatus(0);
            loginLog.setMsg(e.getMsg());
            loginLogService.save(loginLog);
            return R.fail(e.getMsg(),e.getCode());
        } catch (Throwable throwable) {
            throwable.getMessage();
        }

        return R.fail(ResultEnum.SYS_ERROR);
    }
}
