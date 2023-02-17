package com.milk.job.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.model.dto.InterviewDto;
import com.milk.job.model.pojo.Interview;
import com.milk.job.model.vo.InterviewVo;
import com.milk.job.system.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-08 17:24
 */
@Slf4j
@RequestMapping("/job/interview")
@Api(value = "面试管理" ,tags = "interview")
@RestController
public class InterviewController {

    @Resource
    private InterviewService interviewService;

    @PostMapping("/page")
    @ApiOperation(value = "分页获取面试表单")
    public R getPage(@RequestBody InterviewVo interviewVo){

        Page<InterviewDto> interviewDto = interviewService.getPage(interviewVo);

        return R.success(interviewDto);
    }

    @PostMapping("/user/{id}")
    @ApiOperation(value = "跟据用户id获取")
    public R getByUserId(@PathVariable("id") Integer id,@RequestBody InterviewVo interviewVo){

        Page<Map<String,Object>> page = interviewService.getInterviewByUserId(id,interviewVo);

        return R.success(page);
    }

    @GetMapping("/hr/{id}")
    @ApiOperation(value = "跟据HRid获取")
    public R getByHRId(@PathVariable("id") Integer id){

        List<InterviewDto> interviewDto = interviewService.getInterviewByHrId(id);

        return R.success(interviewDto);
    }

    @PostMapping("/pass")
    @ApiOperation(value = "通过面试")
    public R passInterview(@RequestBody List<Interview> interview){

        log.info("{}",interview);

        interviewService.passInterview(interview);
        return R.success("操作成功！");
    }

    @PostMapping("/refuse")
    @ApiOperation(value = "拒绝面试")
    public R refuseInterview(@RequestBody List<Interview> interview){

        log.info("{}",interview);
        interviewService.refuseInterview(interview);

        return R.success("操作成功！");
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除面试信息")
    public R delInterview(@RequestBody List<Integer> ids){
        interviewService.removeByIds(ids);
        return R.success("操作成功!");
    }



}
