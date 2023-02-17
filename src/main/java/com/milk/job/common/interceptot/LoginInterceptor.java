package com.milk.job.common.interceptot;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.common.log.Log;
import com.milk.job.common.utils.TokenUtils;
import com.milk.job.model.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-11 12:19
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        log.info("请求URL：{}",request.getRequestURI());

        if (StringUtils.isEmpty(token)){
            log.info("{}","未登录！");
            throw new CustomerException(ResultEnum.LOGIN_AUTH);
        }

        Integer userId = TokenUtils.getUserId(token);

        if (userId == null){
            log.info("token错误！");
            throw new CustomerException(ResultEnum.LOGIN_AUTH);
        }
//        String key  = "LoginUserInfo"+"::"+userId;
//        String  str= (String)redisTemplate.opsForValue().get(key);
//
//        User user = JSONObject.parseObject(str, User.class);
//
//        log.info("{}",user);
//
//        if(user.getId()!= userId){
//            log.info("token错误！二次验证");
//            throw new CustomerException(ResultEnum.LOGIN_AUTH);
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
