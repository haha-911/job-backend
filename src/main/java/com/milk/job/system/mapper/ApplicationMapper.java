package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.pojo.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-12 15:01
 */

public interface ApplicationMapper extends BaseMapper<Application> {


    void batchRemoveById(@Param("ids") List<Integer> ids);

    void batchChangeState(@Param("ids") List<Integer> ids);

    void batchRefuse(@Param("ids") List<Integer> ids);

    Page<Map<String, Object>> getApplyByUserId(Page<Map<String ,Object>> page,@Param("id") Integer id,@Param("state") String state);
}