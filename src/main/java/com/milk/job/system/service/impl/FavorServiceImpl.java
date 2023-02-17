package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.FavorDto;
import com.milk.job.model.vo.LimitVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.system.mapper.FavorMapper;
import com.milk.job.model.pojo.Favor;
import com.milk.job.system.service.FavorService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@Service
public class FavorServiceImpl extends ServiceImpl<FavorMapper, Favor> implements FavorService{

    @Override
    public Page<Map<String,Object>> getFavorByUserId(Integer id, LimitVo limitVo) {
        Page<Map<String,Object>> page = new Page<>(limitVo.getPage(),limitVo.getPageSize());
        return baseMapper.getFavorByUserId(page,id);
    }
}
