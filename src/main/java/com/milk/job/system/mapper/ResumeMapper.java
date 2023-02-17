package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.ResumeDto;
import com.milk.job.model.pojo.Resume;

import java.util.List;

import com.milk.job.model.vo.ResumeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:09
 */

public interface ResumeMapper extends BaseMapper<Resume> {

    int batchInsert(@Param("list") List<Resume> list);

    ResumeDto getResumeById(@Param("id") Integer id);

    String getExpectByUserId(@Param("id") Integer userId);

    Integer getIdByUserId(@Param("id") Integer userId);

//    Page<ResumeDto> getPageResume(Page<ResumeDto> page, @Param("resumeVo") ResumeVo resumeVo);

}