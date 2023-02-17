package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.ResumeDto;
import com.milk.job.model.pojo.Resume;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.ResumeVo;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface ResumeService extends IService<Resume>{


    int batchInsert(List<Resume> list);

    ResumeDto getResumeById(Integer id);

    Page<ResumeDto> getPageResume(Integer page, Integer pageSize);

    Resume getResumeByUserId(Integer id);

    String getExpectByUserId(Integer userId);

    Integer getIdByUserId(Integer userId);
}
