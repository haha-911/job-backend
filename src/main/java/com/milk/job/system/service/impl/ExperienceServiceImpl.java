package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.milk.job.model.pojo.Education;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.milk.job.system.mapper.ExperienceMapper;
import com.milk.job.model.pojo.Experience;
import com.milk.job.system.service.ExperienceService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
public class ExperienceServiceImpl extends ServiceImpl<ExperienceMapper, Experience> implements ExperienceService{

    @Resource
    private ExperienceMapper experienceMapper;

    @Override
    public int batchInsert(List<Experience> list) {
        return experienceMapper.batchInsert(list);
    }

    @Override
    public void delExperByResumeId(Integer resume_id) {

        LambdaQueryWrapper<Experience> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Experience::getResumeId,resume_id);

        experienceMapper.delete(queryWrapper);

    }

    @Override
    public List<Experience> getExperByResumeId(Integer resume_id) {

        LambdaQueryWrapper<Experience> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Experience::getResumeId,resume_id);
        return this.list(queryWrapper);
    }
}
