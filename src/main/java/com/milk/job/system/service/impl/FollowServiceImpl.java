package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.FollowDto;
import com.milk.job.model.vo.LimitVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.invoke.CallSite;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.model.pojo.Follow;
import com.milk.job.system.mapper.FollowMapper;
import com.milk.job.system.service.FollowService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService{

    @Override
    public Page<Map<String,Object>> getFollowByUserId(Integer id, LimitVo limitVo) {

        Page<Map<String ,Object>> page = new Page<>(limitVo.getPage(),limitVo.getPageSize());

        Page<Map<String,Object>> list = baseMapper.getFollowByUserId(page,id);
        return list;
    }
}
