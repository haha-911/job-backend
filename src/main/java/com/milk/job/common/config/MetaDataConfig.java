package com.milk.job.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description 元数据映射器 ，需要在使用的字段上家住距@TableFile()
 * @Author Milk
 * @Date 2022-12-10 14:50
 */

@Component
public class MetaDataConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
//        职位发布时间
//        metaObject.setValue("releaseDate",LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        metaObject.setValue("updateTime", LocalDateTime.now());

    }

}
