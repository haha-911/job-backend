package com.milk.job.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.PositionDto;
import com.milk.job.model.pojo.Notify;
import com.milk.job.model.pojo.Position;
import com.milk.job.model.vo.LimitVo;
import com.milk.job.model.vo.PositionVo;
import com.milk.job.system.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 23:10
 */

@Slf4j
@RestController
@Api(value = "职位",tags = "position")
@RequestMapping("/job/position")
public class PositionController {

    @Resource
    private PositionService positionService;

    @GetMapping("/all")
    @ApiOperation(value = "查询所有职位")
    public R getAll(){
        List<PositionDto> positionList = positionService.getAll();
        return R.success(positionList);
    }

    @GetMapping("/company/{id}")
    @ApiOperation(value = "根据公司id查询职位")
    public R getPositionByCompanyId(@PathVariable("id") Integer id){
        List<Position> positionList = positionService.getPositionByCompanyId(id);
        return R.success(positionList);
    }

    @GetMapping("/get/count/{id}")
    @ApiOperation(value = "根据公司id查询职位数量")
    public R getCount(@PathVariable("id") Integer id){

        LambdaQueryWrapper<Position> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Position::getCompanyId,id);

        int count = positionService.count(queryWrapper);
        return R.success(count);
    }
    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据ID查询职位")
    public R getPositionById(@PathVariable("id") Integer id){

        PositionDto position = positionService.getPositionById(id);

        return R.success(position);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询职位")
    public R getPage(@RequestBody PositionVo positionVo){
        Page<PositionDto> pageInfo = positionService.getPage(positionVo);
        return R.success(pageInfo);
    }

    @PostMapping("/get/job")
    @ApiOperation(value = "前端分页查询职位")
    public R fontPosition(@RequestBody PositionVo positionVo){
        Page<PositionDto> pageInfo = positionService.getJob(positionVo);
        return R.success(pageInfo);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除职位")
    public R delPosition(@PathVariable("id") Integer id){
        positionService.removeById(id);

        return R.success("删除成功！");
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加职位")
    public R addPosition(@RequestBody Position position){
        position.setReleaseDate(LocalDateTime.now());

        positionService.save(position);

        return R.success("添加成功！");
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改职位信息")
    public R updatePosition(@RequestBody Position Position){
        if (Position.getId() == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        positionService.updateById(Position);

        return R.success("修改成功!");
    }

    @PostMapping("/state/{id}/{status}")
    @ApiOperation(value = "上下架职位")
    public R changeState(@PathVariable("id") Integer id,@PathVariable("status") Integer status){

        positionService.changeState(id,status);

        return R.success("状态修改成功！");
    }

    @PostMapping("/pass")
    @ApiOperation(value = "审核职位")
    public R toExaminePosition(@RequestBody List<Notify> notifys){
        log.info("{}",notifys);

        positionService.toExaminePosition(notifys);

        return R.success("批量审核成功！！");
    }

    @PostMapping("/refuse")
    @ApiOperation("驳回职位")
    public R batchRefusePosition(@RequestBody List<Notify> notifys){

        log.info("数据：{}",notifys);

        positionService.refusePosition(notifys);

        return R.success("成功驳回！");

    }

    @DeleteMapping("/batchdel")
    @ApiOperation(value = "批量删除职位信息")
    public R batchDelPosition(@RequestBody List<Integer> ids){

        positionService.batchDelete(ids);
        return R.success("删除成功！");

    }

    @PostMapping("/toExamine/all")
    @ApiOperation(value = "查询所有待审核职位")
    public R selExaminePosition(@RequestBody PositionVo positionVo){

        Page<PositionDto> positionDtoList = positionService.selExaminePosition(positionVo);

        return R.success(positionDtoList);
    }

    @PostMapping("/category/{id}")
    @ApiOperation(value = "根据分类ID查询职位")
    public R getPositionByCategoryId(@RequestBody LimitVo limitVo,@PathVariable("id") Integer id){

        Page<PositionDto> positionDtoList = positionService.getPositionByCategoryId(limitVo,id);

        return R.success(positionDtoList);
    }

    @PostMapping("/hr/{id}")
    @ApiOperation(value = "根据HR——ID查询职位信息")
    public R getPositionByHRId(@RequestBody LimitVo limitVo,@PathVariable("id") Integer id){

        Page<PositionDto> positionDtoList = positionService.getPositionByHRId(limitVo,id);

        return R.success(positionDtoList);
    }
}
