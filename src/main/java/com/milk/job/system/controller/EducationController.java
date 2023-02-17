package com.milk.job.system.controller;

import com.milk.job.common.R;
import com.milk.job.model.pojo.Education;
import com.milk.job.system.service.EducationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 17:35
 */

@Slf4j
@RestController
@RequestMapping("/job/edu")
@Api(value = "教育经历" ,tags = "education")
public class EducationController {

    @Resource
    private EducationService educationService;


    @ApiOperation(value = "由简历ID获取教育经历")
    @GetMapping("/{id}")
    public R getEduByResumeId(@PathVariable("id") Integer resume_id){

        List<Education> educationList = educationService.getEduByResumeId(resume_id);

        return R.success(educationList);

    }


    @ApiOperation(value = "修改教育经历")
    @PutMapping("/update")
    public R updateEducation(@RequestBody Education education){

         educationService.updateById(education);

        return R.success("修改成功！");

    }

    @ApiOperation(value = "删除教育经历")
    @DeleteMapping("/del/{id}")
    public R delEducation(@PathVariable("id") Integer id){

        educationService.removeById(id);

        return R.success("删除成功！");

    }

    @ApiOperation(value = "添加教育经历")
    @PostMapping("/add")
    public R addEducation(@RequestBody Education education){

        educationService.save(education);

        return R.success("添加成功！");

    }
}
