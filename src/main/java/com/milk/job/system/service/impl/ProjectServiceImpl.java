package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milk.job.model.pojo.Education;
import com.milk.job.system.mapper.PositionMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.milk.job.system.mapper.ProjectMapper;
import com.milk.job.model.pojo.Project;
import com.milk.job.system.service.ProjectService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService{

    @Override
    public int batchInsert(List<Project> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<Project> getProjectByResumeId(Integer resume_id) {

        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getResumeId,resume_id);

        return this.list(queryWrapper);
    }

}
