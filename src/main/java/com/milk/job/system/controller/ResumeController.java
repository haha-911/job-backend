package com.milk.job.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.ResumeDto;
import com.milk.job.model.pojo.Resume;
import com.milk.job.model.vo.ResumeVo;
import com.milk.job.system.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 15:47
 */
@Api(value = "简历相关" ,tags = "resume")
@RestController
@RequestMapping("/job/resume")
@Slf4j
public class ResumeController {

    @Resource
    private ResumeService resumeService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据用户id获取完整简历信息")
    public R getResumeById(@PathVariable("id") Integer id){
       ResumeDto resumeDto =  resumeService.getResumeById(id);
       return R.success(resumeDto);
    }

    @GetMapping("/expect/{id}")
    @ApiOperation(value = "根据用户id获取求职期望")
    public R getExpectByUserId(@PathVariable("id") Integer userId){
        String str =  resumeService.getExpectByUserId(userId);
        return R.success(str);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "根据用户id获取基本简历信息")
    public R getResumeByUserId(@PathVariable("id") Integer userId){

        Resume resume =  resumeService.getResumeByUserId(userId);

        return R.success(resume);

    }

    @PostMapping("/page")
    @ApiOperation(value = "获取所有完整简历信息")
    public R getResumeById(@RequestParam Integer page,@RequestParam Integer pageSize){
        Page<ResumeDto> pageInfo = resumeService.getPageResume(page,pageSize);

        return R.success(pageInfo);

    }


    @PostMapping("/add")
    @ApiOperation(value = "添加简历基本信息")
    public R addResume(@RequestBody Resume resume){

        if(resume.getUserId() == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }
        resumeService.save(resume);
        Integer id = resume.getId();

        return R.success("添加成功！").add("id",id);

    }

    @PutMapping("/update")
    @ApiOperation(value = "修改简历信息基本信息")
    public R updateResume(@RequestBody Resume resume){

        log.info("获取的数据：{}",resume);

        resumeService.updateById(resume);

        return R.success("修改成功！");

    }


    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除简历信息基本信息")
    public R delResume(@PathVariable("id") Integer id){

        resumeService.removeById(id);

        return R.success("删除成功！");

    }

}
