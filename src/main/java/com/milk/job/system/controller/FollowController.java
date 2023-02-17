package com.milk.job.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.FavorDto;
import com.milk.job.model.dto.FollowDto;
import com.milk.job.model.pojo.Favor;
import com.milk.job.model.pojo.Follow;
import com.milk.job.model.vo.LimitVo;
import com.milk.job.system.service.FollowService;
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
 * @Date 2022-12-14 10:44
 */
@Slf4j
@RestController
@Api(value = "收藏公司",tags = "follow")
@RequestMapping("/job/follow")
public class FollowController {

    @Resource
    private FollowService followService;

    @PostMapping("/user/{id}")
    @ApiOperation(value = "查询用户关注的公司")
    public R getFollowByUserId(@PathVariable("id") Integer id, @RequestBody LimitVo limitVo){

        Page<Map<String,Object>> list = followService.getFollowByUserId(id,limitVo);

        return R.success(list);

    }


    @PostMapping("/add")
    @ApiOperation(value = "关注公司")
    public R addFollow(@RequestBody Follow follow){
        if (follow.getUserId() ==null || follow.getCompanyId() ==null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        followService.save(follow);

        return R.success("关注成功");

    }
    @GetMapping("/get/{userId}/{companyId}")
    @ApiOperation(value = "根据用户ID和公司ID查看是否被关注")
    public R getUserFavorByCompanyId(@PathVariable("userId") Integer userId,@PathVariable("companyId") Integer companyId){
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getUserId,userId).eq(Follow::getCompanyId,companyId);
        Follow Follow = followService.getOne(queryWrapper);
        if (Follow ==null){
            return R.success(0);
        }
        return R.success(1);

    }

    @DeleteMapping("/cancel/{userId}/{companyId}")
    @ApiOperation(value = "根据用户ID和公司ID取消关注")
    public R delFavor(@PathVariable("userId") Integer userId,@PathVariable("companyId") Integer companyId){
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getUserId,userId).eq(Follow::getCompanyId,companyId);
         followService.remove(queryWrapper);
        return R.success("已取消关注！");

    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "取消关注")
    public R delFavor(@PathVariable("id") Integer id){

        followService.removeById(id);

        return R.success("已取消关注！");

    }
}
