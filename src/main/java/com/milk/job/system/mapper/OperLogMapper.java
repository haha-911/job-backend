package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milk.job.model.pojo.OperLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-10 22:23
 */

public interface OperLogMapper extends BaseMapper<OperLog> {


    void delOperlog(@Param("ids") List<Integer> ids);
}