package com.milk.job.system.controller;

import com.milk.job.common.R;
import com.milk.job.model.pojo.Education;
import com.milk.job.model.pojo.Experience;
import com.milk.job.system.service.ExperienceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 18:50
 */

@Slf4j
@RestController
@RequestMapping("/job/exper")
@Api(value = "实习经历" ,tags = "experience")
public class ExperienceController {


    @Resource
    private ExperienceService experienceService;

    @ApiOperation(value = "由简历ID获取实习经历")
    @GetMapping("/{id}")
    public R getExperienceByResumeId(@PathVariable("id") Integer resume_id){

        List<Experience> experiences = experienceService.getExperByResumeId(resume_id);

        return R.success(experiences);

    }


    @ApiOperation(value = "修改实习经历")
    @PutMapping("/update")
    public R updateExperience(@RequestBody Experience experience){

        experienceService.updateById(experience);

        return R.success("修改成功！");

    }

    @ApiOperation(value = "删除实习经历")
    @DeleteMapping("/del/{id}")
    public R delExperienceBy(@PathVariable("id") Integer id){

        experienceService.removeById(id);

        return R.success("删除成功！");

    }

    @ApiOperation(value = "添加实习经历")
    @PostMapping("/add")
    public R addExperience(@RequestBody Experience experience){

        experienceService.save(experience);

        return R.success("添加成功！");

    }
}
