package com.milk.job.system.service;

import java.util.List;

import com.milk.job.model.pojo.Education;
import com.milk.job.model.pojo.Experience;
import com.baomidou.mybatisplus.extension.service.IService;
    
/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface ExperienceService extends IService<Experience>{


    int batchInsert(List<Experience> list);
    

    void delExperByResumeId(Integer resume_id);

    List<Experience> getExperByResumeId(Integer resume_id);

}
