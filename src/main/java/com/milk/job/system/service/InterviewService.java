package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.InterviewDto;
import com.milk.job.model.pojo.Interview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.InterviewVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

public interface InterviewService extends IService<Interview>{

    void addInterView(Interview interview);

    Page<InterviewDto> getPage(InterviewVo interviewVo);

    Page<Map<String,Object>> getInterviewByUserId( Integer id ,InterviewVo interviewVo);

    List<InterviewDto> getInterviewByHrId(Integer id);

    void passInterview( List<Interview> interview);

    void refuseInterview( List<Interview> interview);
}
