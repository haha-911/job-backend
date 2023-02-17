package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.FollowDto;
import com.milk.job.model.pojo.Follow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.LimitVo;

import java.util.List;
import java.util.Map;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

public interface FollowService extends IService<Follow>{


    Page<Map<String,Object>> getFollowByUserId(Integer id, LimitVo limitVo);
}
