package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.milk.job.model.pojo.Education;
import com.milk.job.system.mapper.EducationMapper;
import com.milk.job.system.service.EducationService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
public class EducationServiceImpl extends ServiceImpl<EducationMapper, Education> implements EducationService{

    @Resource
    private EducationMapper educationMapper;

    @Override
    public int batchInsert(List<Education> list) {
        return educationMapper.batchInsert(list);
    }

    @Override
    public void delEduByResumeId(Integer resume_id) {

        LambdaQueryWrapper<Education> queryMapper = new LambdaQueryWrapper<>();

        queryMapper.eq(Education::getResumeId,resume_id);

        educationMapper.delete(queryMapper);

    }


    @Override
    public List<Education> getEduByResumeId(Integer resume_id) {

        LambdaQueryWrapper<Education> queryMapper = new LambdaQueryWrapper<>();

        queryMapper.eq(Education::getResumeId,resume_id);

        List<Education> educations = this.list(queryMapper);
        return educations;
    }
}
