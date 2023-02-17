package com.milk.job.system.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.JsonObject;
import com.milk.job.common.asynctask.ThreadService;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.ApplicationDto;
import com.milk.job.model.pojo.*;
import com.milk.job.model.vo.ApplicationVo;
import com.milk.job.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.system.mapper.ApplicationMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-12 15:01
 */

@Service
@Slf4j
@Transactional
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService{

    @Resource
    private CompanyService companyService;
    @Resource
    private UserService userService;
    @Resource
    private InterviewService interviewService;
    @Resource
    private PositionService positionService;
    @Resource
    private NotifyService notifyService;
    @Resource
    private ApplicationMapper applicationMapper;
    @Resource
    private ThreadService threadService;

    @Override
    public Page<ApplicationDto> getPage(ApplicationVo applicationVo) {

        Page<Application> page = new Page<>(applicationVo.getPage(), applicationVo.getPageSize());

        LambdaQueryWrapper<Application> applicationQuery = new LambdaQueryWrapper<>();

        applicationQuery.eq(!StringUtils.isEmpty(applicationVo.getCompanyId()),Application::getCompanyId,applicationVo.getCompanyId())
                .eq(!StringUtils.isEmpty(applicationVo.getHrId()),Application::getHrId,applicationVo.getHrId())
                .eq(!StringUtils.isEmpty(applicationVo.getUserId()),Application::getUserId,applicationVo.getUserId())
                .eq(!StringUtils.isEmpty(applicationVo.getPositionId()),Application::getPositionId,applicationVo.getPositionId())
                .eq(!StringUtils.isEmpty(applicationVo.getResumeId()),Application::getResumeId,applicationVo.getResumeId())
                .ge(!StringUtils.isEmpty(applicationVo.getApplyTimeStart()),Application::getApplyTime,applicationVo.getApplyTimeStart())
                .le(!StringUtils.isEmpty(applicationVo.getApplyTimeEnd()),Application::getApplyTime,applicationVo.getApplyTimeEnd());
        if (applicationVo.getState()!=null){
            applicationQuery.eq(applicationVo.getState().equals("0"),Application::getState,applicationVo.getState())
                    .eq(applicationVo.getState().equals("1"),Application::getState,applicationVo.getState())
                    .eq(applicationVo.getState().equals("2"),Application::getState,applicationVo.getState());
        }

        Page<Application> applicationPage = this.page(page, applicationQuery);
        Page<ApplicationDto> applicationDtoPage = new Page<>();

        BeanUtils.copyProperties(applicationPage,applicationDtoPage,"records");

        List<Application> applicationList = applicationPage.getRecords();
        List<ApplicationDto> applicationDtoList = new ArrayList<>();

        for (Application application : applicationList) {
            ApplicationDto applicationDto = new ApplicationDto();
            BeanUtils.copyProperties(application,applicationDto);

            Integer companyId = application.getCompanyId();
            Company company = companyService.getById(companyId);
            if (company !=null){
                applicationDto.setCompanyName(company.getName());
            }

            Integer userId = application.getUserId();
            User user = userService.getById(userId);
            if (user !=null){
                applicationDto.setUserName(user.getUsername());
            }

            Integer hrId = application.getHrId();
            User hr = userService.getById(hrId);
            if (hr !=null){
                applicationDto.setHRName(hr.getUsername());
            }

            Integer positionId = application.getPositionId();
            Position position = positionService.getById(positionId);
            if (position !=null){
                applicationDto.setTitle(position.getTitle());
            }

            applicationDtoList.add(applicationDto);
        }

        applicationDtoPage.setRecords(applicationDtoList);

        return applicationDtoPage;
    }

    @Override
    public void batchRemoveById(List<Integer> ids) {
        applicationMapper.batchRemoveById(ids);
    }

    private void saveInterview(Application application,String address,String memo,String time){
    Interview interview = new Interview();

    interview.setHrId(application.getHrId());
    interview.setUserId(application.getUserId());
    interview.setResumeId(application.getResumeId());
    interview.setCompanyId(application.getCompanyId());
    interview.setPositionId(application.getPositionId());
    interview.setAddress(address);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        interview.setTime(date);
    interview.setMemo(memo);

    interviewService.addInterView(interview);
}

    private void updateState(Integer id,Integer state){

        LambdaUpdateWrapper<Application> queryWrapper =  new LambdaUpdateWrapper<>();

        queryWrapper.eq(Application::getId,id);

        queryWrapper.set(Application::getState,state).set(Application::getUpdateTime, LocalDateTime.now());

        this.update(queryWrapper);
    }



    @Override
    public void batchPass(Map<String,Object> map) {
        List<Integer> ids =(List) map.get("ids");
        String address = (String) map.get("address");
        String memo = (String) map.get("memo");
        String time  = (String) map.get("time");
        for (Integer id: ids) {

            Application application = this.getById(id);
            this.saveInterview(application,address,memo,time);
            Integer state = 1;
            this.updateState(application.getId(),state);
        }

    }
    @Override
    public void batchRefuse(List<Integer> ids) {

        for (Integer id: ids) {
            Integer state = 0;
            this.updateState(id,state);
        }

    }


    @Override
    public Page<Map<String, Object>> getApplyByUserId(Integer id,ApplicationVo applicationVo) {

        Page<Map<String ,Object>> page = new Page<>(applicationVo.getPage(), applicationVo.getPageSize());
        return applicationMapper.getApplyByUserId(page,id,applicationVo.getState());
    }
}
