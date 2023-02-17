package com.milk.job.system.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.asynctask.ThreadService;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.PositionDto;
import com.milk.job.model.pojo.*;
import com.milk.job.model.vo.LimitVo;
import com.milk.job.model.vo.PositionVo;
import com.milk.job.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;

import com.milk.job.system.mapper.PositionMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
@Transactional
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService{

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private CompanyService companyService;
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private NotifyService notifyService;
    @Resource
    private ThreadService threadService;

    @Override
    public PositionDto getPositionById(Integer id) {

        PositionDto positionDto = positionMapper.getPositionById(id);
        return positionDto;
    }

    @Override
    public int batchInsert(List<Position> list) {
        return positionMapper.batchInsert(list);
    }

    @Override
    public List<PositionDto> getAll() {
        return positionMapper.getAll();
    }

    @Override
    public Page<PositionDto> getPage(PositionVo positionVo) {
        Page<Position> page = new Page<>(positionVo.getPage(),positionVo.getPageSize());
        return positionMapper.getPage(page,positionVo);

    }

    @Override
    public void changeState(Integer id, Integer status) {
        if (id ==null || status ==null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }
        int state =0;
        if(status == state){
            state = 1;
        }

        LambdaUpdateWrapper<Position> updateWrapper=new LambdaUpdateWrapper<>();

        updateWrapper.eq(Position::getId,id);

        updateWrapper.set(Position::getState,state);

        this.update(updateWrapper);
    }

    @Override
    public void toExaminePosition(List<Notify> notifies) {

        for (Notify notify : notifies) {
            Notify info = new Notify();
            info.setTitle("你申请的"+notify.getTitle()+"职位审核通过了！");
            info.setUserId(notify.getUserId());
            threadService.addNotify(notifyService,info);

        if (notify.getId() == null) {
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        LambdaUpdateWrapper<Position> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Position::getId, notify.getId()).set(Position::getState, 1);
        this.update(updateWrapper);
    }
    }

    @Override
    public Page<PositionDto> selExaminePosition(PositionVo positionVo) {

        if (positionVo.getState() == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }
        Page<Position> page = new Page<Position>(positionVo.getPage(),positionVo.getPageSize());
        LambdaQueryWrapper<Position> queryWrapper = new LambdaQueryWrapper<>();

        int state;

        if (positionVo.getState().equals("0")){
            state  = 2;
        }else{
            state = 3;
        }
        queryWrapper.eq(Position::getState,state)
                .eq(!StringUtils.isEmpty(positionVo.getCompanyId()),Position::getCompanyId,positionVo.getCompanyId())
                .eq(!StringUtils.isEmpty(positionVo.getCategoryId()),Position::getCategoryId,positionVo.getCategoryId())
                .like(!StringUtils.isEmpty(positionVo.getCity()),Position::getCity,positionVo.getCity());;

        Page<Position> positionPage = this.page(page,queryWrapper);
        return  getPositionDtoPage(positionPage);

    }

    private Page<PositionDto> getPositionDtoPage(Page<Position> positionPage) {
        Page<PositionDto> positionDtoPage = new Page<>();
        List<Position> positionList = positionPage.getRecords();
        BeanUtils.copyProperties(positionPage,positionDtoPage,"records");

        List<PositionDto> positionDtoList = new ArrayList<>();
        for (Position position : positionList) {
            PositionDto positionDto = new PositionDto();
            BeanUtils.copyProperties(position,positionDto);
            Category category = categoryService.getById(position.getCategoryId());
            positionDto.setCategoryName(category.getName());
            Company company = companyService.getById(position.getCompanyId());
            positionDto.setCompanyName(company.getName());
            User user = userService.getById(position.getHrId());
            positionDto.setHrName(user.getUsername());

            positionDtoList.add(positionDto);
        }

        positionDtoPage.setRecords(positionDtoList);


        return positionDtoPage;

    }

    @Override
    public Page<PositionDto> getPositionByCategoryId(LimitVo limitVo,Integer id) {
        Page<Position> page = new Page<>(limitVo.getPage(),limitVo.getPageSize());
        LambdaQueryWrapper<Position> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Position::getCategoryId,id);
        Page<Position> positionPage = this.page(page,queryWrapper);
        return  getPositionDtoPage(positionPage);
    }

    @Override
    public Page<PositionDto> getPositionByHRId(LimitVo limitVo,Integer id) {
        Page<Position> page = new Page<>(limitVo.getPage(),limitVo.getPageSize());
        LambdaQueryWrapper<Position> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Position::getHrId,id);
        Page<Position> positionPage = this.page(page,queryWrapper);
        return  getPositionDtoPage(positionPage);
    }


    @Override
    public void batchDelete(List<Integer> ids) {
        positionMapper.deleteBatchIds(ids);
    }

    @Override
    public void refusePosition(List<Notify> notifys) {
        for (Notify notify : notifys) {
            Notify info = new Notify();
            info.setTitle("你申请的" + notify.getTitle() + "职位被驳回了！");
            info.setUserId(notify.getUserId());
            info.setContent(notify.getContent());
            threadService.addNotify(notifyService, info);

            if (notify.getId() == null) {
                throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
            }

            LambdaUpdateWrapper<Position> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Position::getId, notify.getId()).set(Position::getState, 3);
            this.update(updateWrapper);
        }
    }

    @Override
    public Page<PositionDto> getJob(PositionVo positionVo) {
        Page<Position> page = new Page<>(positionVo.getPage(),positionVo.getPageSize());
        return positionMapper.getJob(page,positionVo);
    }

    @Override
    public List<Position> getPositionByCompanyId (Integer id){
        List<Position> positionList = positionMapper.getPositionByCompanyId(id);
        return positionList;
    }

}
