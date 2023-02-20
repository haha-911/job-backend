package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.asynctask.ThreadService;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.InterviewDto;
import com.milk.job.model.pojo.Notify;
import com.milk.job.model.vo.InterviewVo;
import com.milk.job.system.service.NotifyService;
import com.sun.jdi.event.ThreadStartEvent;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.model.pojo.Interview;
import com.milk.job.system.mapper.InterviewMapper;
import com.milk.job.system.service.InterviewService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview> implements InterviewService{

    @Resource
    private InterviewMapper interviewMapper;
    @Resource
    private ThreadService threadService;
    @Resource
    private NotifyService notifyService;

    @Override
    public void addInterView(Interview interview) {

        if (interview.getAddress() ==null){
            throw new CustomerException("面试地址不能为空！",500);
        }

        if (interview.getCompanyId()==null || interview.getHrId() == null || interview.getResumeId()==null|| interview.getUserId() == null || interview.getPositionId()==null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        this.save(interview);
    }

    @Override
    public Page<InterviewDto> getPage(InterviewVo interviewVo) {

        Page<InterviewDto> page = new Page<>(interviewVo.getPage(),interviewVo.getPageSize());

        return interviewMapper.getPage(page,interviewVo) ;
    }

    @Override
    public Page<Map<String,Object>> getInterviewByUserId( Integer id ,InterviewVo interviewVo){

        Page<Map<String,Object>> page = new Page<>(interviewVo.getPage(),interviewVo.getPageSize());

         return interviewMapper.getInterviewByUserId(page,id,interviewVo.getState());
    }

    @Override
    public Page<InterviewDto> getInterviewByHrId(InterviewVo  interviewVo) {

        Page<Interview> page = new Page<>(interviewVo.getPage(),interviewVo.getPageSize());
        return interviewMapper.getInterviewByHrId(page,interviewVo);
    }

    @Override
    public void passInterview(List<Interview> interviews) {

        for (Interview interview : interviews) {

            if (interview.getId() == null) {
                throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
            }

            if (interview.getStatus() != 2) {
                throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
            }
            interview.setStatus(1);
            Notify notify = new Notify();
            notify.setUserId(interview.getUserId());
            notify.setTitle("你的面试通过啦！");
            threadService.addNotify(notifyService,notify);
            this.updateById(interview);
        }

    }

    @Override
    public void refuseInterview(List<Interview> interviews) {
        for (Interview interview : interviews) {

            if (interview.getId() == null) {
                throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
            }

            if (interview.getStatus() != 2) {
                throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
            }

            interview.setStatus(0);
            Notify notify = new Notify();
            notify.setUserId(interview.getUserId());
            notify.setTitle("你的面试被拒绝啦！");
            threadService.addNotify(notifyService,notify);
            this.updateById(interview);

        }
    }
}
