package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.InterviewDto;
import com.milk.job.model.pojo.Interview;
import com.milk.job.model.vo.InterviewVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

public interface InterviewMapper extends BaseMapper<Interview> {

    Page<InterviewDto> getPage(Page<InterviewDto> page, @Param("interviewVo") InterviewVo interviewVo);

    Page<Map<String,Object>> getInterviewByUserId( Page<Map<String,Object>> page,@Param("id") Integer id,@Param("state") String  state );

    List<InterviewDto> getInterviewByHrId(@Param("id") Integer id);
}