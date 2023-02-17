package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.FollowDto;
import com.milk.job.model.pojo.Follow;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

public interface FollowMapper extends BaseMapper<Follow> {


    Page<Map<String,Object>> getFollowByUserId(Page<Map<String,Object>> map,@Param("id") Integer id);
}