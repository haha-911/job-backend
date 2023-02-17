package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.FavorDto;
import com.milk.job.model.pojo.Favor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-14 10:12
 */

public interface FavorMapper extends BaseMapper<Favor> {

    Page<Map<String, Object>> getFavorByUserId(@Param("page") Page<Map<String,Object>> page, @Param("id") Integer id);
}