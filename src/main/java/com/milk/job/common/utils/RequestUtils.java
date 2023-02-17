package com.milk.job.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-17 16:20
 */
public class RequestUtils {

    public static HttpServletRequest getRequest(){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        return requestAttributes.getRequest();
    }
}
