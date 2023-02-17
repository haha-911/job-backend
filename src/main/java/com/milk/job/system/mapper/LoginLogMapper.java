package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milk.job.model.pojo.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-12 18:19
 */

public interface LoginLogMapper extends BaseMapper<LoginLog> {


    LoginLog getPrevLogin(@Param("name") String  name);

    void delLoginlog(@Param("ids")List<Integer> ids);


}