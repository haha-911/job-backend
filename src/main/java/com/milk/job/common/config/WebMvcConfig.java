package com.milk.job.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.milk.job.common.interceptot.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.nio.charset.Charset;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-11 12:17
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;


    /*
    * 登录拦截器
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //定义排除swagger访问的路径配置
        String[] swaggerExcludes=new String[]{"/*.html","/swagger-resources/**","/webjars/**","/favicon.ico","/v2/**"};
        String[] userExcludes = new String[]{"/user/login","/registry","/sendCode","/job/position/get/**","/job/category/all","/job/company/get/**"};

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor);

        interceptorRegistration.addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns(swaggerExcludes)
                .excludePathPatterns(userExcludes);


    }

    /*
    * 跨域配置
    * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    /*
    * 日期转换器
    * */
    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
        log.info("添加消息转换器");
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        config.setCharset(Charset.forName("UTF-8"));
        config.setSerializerFeatures(
                //输出类名
//                SerializerFeature.WriteClassName,
                //输出map中value为null的数据
                SerializerFeature.WriteMapNullValue,
                //json格式化
                SerializerFeature.PrettyFormat,
                //输出空list为[]，而不是null
                SerializerFeature.WriteNullListAsEmpty,
                //输出空string为""，而不是null
                SerializerFeature.WriteNullStringAsEmpty
        );
        converter.setFastJsonConfig(config);
        return converter;
    }
}
