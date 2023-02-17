package com.milk.job.system.controller;

import com.milk.job.common.R;
import com.milk.job.model.pojo.Education;
import com.milk.job.model.pojo.Project;
import com.milk.job.system.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-11 21:56
 */

@Slf4j
@RestController
@RequestMapping("/job/project")
@Api(value = "项目经验" ,tags = "project")
public class ProjectController {

    @Resource
    private ProjectService projectService;


    @ApiOperation(value = "由简历ID获取项目经历")
    @GetMapping("/{id}")
    public R getProjectByResumeId(@PathVariable("id") Integer resume_id){

        List<Project> educationList = projectService.getProjectByResumeId(resume_id);

        return R.success(educationList);

    }


    @ApiOperation(value = "修改项目经历")
    @PutMapping("/update")
    public R updateProject(@RequestBody Project project){

        projectService.updateById(project);

        return R.success("修改成功！");

    }

    @ApiOperation(value = "删除项目经历")
    @DeleteMapping("/del/{id}")
    public R delProject(@PathVariable("id") Integer id){

        projectService.removeById(id);

        return R.success("删除成功！");

    }

    @ApiOperation(value = "添加项目经历")
    @PostMapping("/add")
    public R addProject(@RequestBody Project project){

        projectService.save(project);

        return R.success("添加成功！");

    }
}
