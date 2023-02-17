package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.ResumeDto;
import com.milk.job.model.pojo.Education;
import com.milk.job.model.pojo.Experience;
import com.milk.job.model.pojo.Project;
import com.milk.job.model.vo.ResumeVo;
import com.milk.job.system.mapper.EducationMapper;
import com.milk.job.system.mapper.ExperienceMapper;
import com.milk.job.system.service.EducationService;
import com.milk.job.system.service.ExperienceService;
import com.milk.job.system.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.model.pojo.Resume;
import java.util.List;
import com.milk.job.system.mapper.ResumeMapper;
import com.milk.job.system.service.ResumeService;
import org.springframework.transaction.annotation.Transactional;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
@Transactional
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService{

    @Resource
    private ResumeMapper resumeMapper;
    @Resource
    private EducationService educationService;
    @Resource
    private ExperienceService experienceService;
    @Resource
    private ProjectService projectService;

    @Override
    public int batchInsert(List<Resume> list) {
        return resumeMapper.batchInsert(list);
    }

    @Override
    public ResumeDto getResumeById(Integer id) {
        return resumeMapper.getResumeById(id);
    }

    @Override
    public Page<ResumeDto> getPageResume(Integer page, Integer pageSize) {

        Page<Resume> pageResume = new Page<>(page,pageSize);

        Page<Resume> pageInfo = this.page(pageResume);

        List<Resume> resumeList = pageInfo.getRecords();

        Page<ResumeDto> resumeDtoPage = new Page<>();

        BeanUtils.copyProperties(pageInfo,resumeDtoPage,"records");

        List<ResumeDto> resumeDtoList = new ArrayList<>();

        for (Resume resume : resumeList) {

            ResumeDto resumeDto = new ResumeDto();
            BeanUtils.copyProperties(resume,resumeDto);
            Integer id = resume.getId();
            List<Education> educations = educationService.getEduByResumeId(id);
            resumeDto.setEducation(educations);
            List<Experience> experiences = experienceService.getExperByResumeId(id);
            resumeDto.setExperience(experiences);
            List<Project> projects = projectService.getProjectByResumeId(id);
            resumeDto.setProject(projects);

            resumeDtoList.add(resumeDto);

        }

        Page<ResumeDto> result = resumeDtoPage.setRecords(resumeDtoList);

        return result;
    }

    @Override
    public Resume getResumeByUserId(Integer id) {
        LambdaQueryWrapper<Resume> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resume::getUserId,id);
        Resume resume = this.getOne(queryWrapper);
        return resume;
    }

    @Override
    public String getExpectByUserId(Integer userId) {
        return resumeMapper.getExpectByUserId(userId);
    }

    @Override
    public Integer getIdByUserId(Integer userId) {

        return resumeMapper.getIdByUserId(userId);
    }
}
