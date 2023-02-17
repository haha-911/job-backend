package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milk.job.model.pojo.Experience;
import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface ExperienceMapper extends BaseMapper<Experience> {
    int batchInsert(@Param("list") List<Experience> list);
}