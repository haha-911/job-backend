package com.milk.job.common.log;

import java.lang.annotation.*;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-10 22:29
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public OperType businessType() default OperType.OTHER;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;
}


