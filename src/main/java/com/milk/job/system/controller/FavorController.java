package com.milk.job.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.FavorDto;
import com.milk.job.model.pojo.Favor;
import com.milk.job.model.vo.LimitVo;
import com.milk.job.system.service.FavorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-14 10:20
 */

@Slf4j
@RestController
@Api(value = "收藏职位",tags = "favor")
@RequestMapping("/job/favor")
public class FavorController {


    @Resource
    private FavorService favorService;


    @PostMapping("/user/{userId}")
    @ApiOperation(value = "查询用户收藏的职位")
    public R getFavorByUserId(@PathVariable("userId") Integer id,@RequestBody LimitVo limitVo){
        Page<Map<String,Object>> list = favorService.getFavorByUserId(id,limitVo);
        return R.success(list);
    }


    @PostMapping("/add")
    @ApiOperation(value = "收藏职位")
    public R addFavor(@RequestBody Favor favor){
        if (favor.getUserId() ==null || favor.getPositionId() ==null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        favorService.save(favor);

        return R.success("收藏成功");

    }


    @GetMapping("/get/{userId}/{positionId}")
    @ApiOperation(value = "查看职位是否被用户收藏")
    public R getUserFavorByPositionId(@PathVariable("userId") Integer userId,@PathVariable("positionId") Integer positionId){
        LambdaQueryWrapper<Favor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favor::getUserId,userId).eq(Favor::getPositionId,positionId);
        Favor favor = favorService.getOne(queryWrapper);
        if (favor ==null){
           return R.success(0);
        }

        return R.success(1);


    }


    @DeleteMapping("/cancel/{userId}/{positionId}")
    @ApiOperation(value = "根据用户id和职位id取消收藏")
    public R removeFavor(@PathVariable("userId") Integer userId,@PathVariable("positionId") Integer positionId){
        LambdaQueryWrapper<Favor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favor::getUserId,userId).eq(Favor::getPositionId,positionId);
        favorService.remove(queryWrapper);
        return R.success("已取消收藏！");
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "根据用户id和职位id取消收藏")
    public R removeFavor(@PathVariable("id") Integer id){
        favorService.removeById(id);
        return R.success("已取消收藏！");
    }

}
