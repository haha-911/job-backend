package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milk.job.model.pojo.Project;
import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface ProjectMapper extends BaseMapper<Project> {
    int batchInsert(@Param("list") List<Project> list);
}