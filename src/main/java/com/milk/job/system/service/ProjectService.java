package com.milk.job.system.service;

import java.util.List;

import com.milk.job.model.pojo.Education;
import com.milk.job.model.pojo.Project;
import com.baomidou.mybatisplus.extension.service.IService;
    
/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface ProjectService extends IService<Project>{


    int batchInsert(List<Project> list);

    List<Project> getProjectByResumeId(Integer resume_id);

}
