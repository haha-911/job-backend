package com.milk.job.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.ApplicationDto;
import com.milk.job.model.pojo.Application;
import com.milk.job.model.pojo.Notify;
import com.milk.job.model.vo.ApplicationVo;
import com.milk.job.system.service.ApplicationService;
import com.milk.job.system.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-12 15:05
 */
@Slf4j
@RestController
@Api(value = "申请模块",tags = "application")
@RequestMapping("/job/application")
public class ApplicationController {

    @Resource
    private ApplicationService applicationService;



    @PostMapping("/page")
    @ApiOperation(value = "根据条件查询申请表单")
    public R getPage(@RequestBody ApplicationVo applicationVo){

        Page<ApplicationDto> page = applicationService.getPage(applicationVo);


        return R.success(page);
    }

    @PostMapping("/add")
    @ApiOperation(value = "用户投递简历")
    public R addApply(@RequestBody Application application){

        if(application.getCompanyId() == null || application.getUserId() == null ||
                application.getPositionId() == null ||
                application.getHrId() == null || application.getResumeId() == null ){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }
        application.setApplyTime(LocalDateTime.now());

        applicationService.save(application);

        return R.success("投递成功！");
    }

    @PostMapping("/isApply")
    @ApiOperation(value = "用户是否投递简历")
    public R userIsApply(@RequestBody Application application){

        if(application.getUserId() == null || application.getPositionId() == null ){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Application::getUserId,application.getUserId())
        .eq(Application::getPositionId,application.getPositionId());

        int count = applicationService.count(queryWrapper);

        if(count == 1){
            return R.success(1);
        }

        return R.success(0);
    }


    @PostMapping("/user/{id}")
    @ApiOperation(value = "由用户id获取用户申请的职位")
    public R getApplyByUserId(@PathVariable("id") Integer id,@RequestBody ApplicationVo applicationVo){

        Page<Map<String ,Object>> applyList = applicationService.getApplyByUserId(id,applicationVo);

        return R.success(applyList);
    }

    @PostMapping("/updateApplyTime")
    @ApiOperation(value = "测试方法（修改所有的发布时间）")
    public R setApplyTime(){

        LambdaUpdateWrapper<Application> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Application::getApplyTime, LocalDateTime.now());
        applicationService.update(updateWrapper);
        return R.success("修改成功！");
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "根据id删除申请")
    public R delApplication(@PathVariable("id") Integer id){
        applicationService.removeById(id);
        return R.success("删除成功！");
    }

    @DeleteMapping("/batchDel")
    @ApiOperation(value = "根据id批量删除申请")
    public R batchRemoveById(@RequestBody List<Integer> ids){
        applicationService.batchRemoveById(ids);
        return R.success("删除成功！");
    }

    @PostMapping("/pass")
    @ApiOperation(value = "通过申请")
    public R batchPass(@RequestBody Map<String,Object> map){
        log.info("{}",map);
        applicationService.batchPass(map);
        return R.success("已通过申请！");
    }

    @PostMapping("/refuse")
    @ApiOperation(value = "驳回申请")
    public R batchRefuse(@RequestBody List<Integer> ids){
        applicationService.batchRefuse(ids);
        return R.success("已驳回申请！");
    }


}
