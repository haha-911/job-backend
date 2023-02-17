package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.FavorDto;
import com.milk.job.model.pojo.Favor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.LimitVo;

import java.util.List;
import java.util.Map;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

public interface FavorService extends IService<Favor>{


    Page<Map<String,Object>> getFavorByUserId(Integer id, LimitVo limitVo);
}
