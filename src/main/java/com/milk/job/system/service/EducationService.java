package com.milk.job.system.service;

import java.util.List;
import com.milk.job.model.pojo.Education;
import com.baomidou.mybatisplus.extension.service.IService;
    
/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface EducationService extends IService<Education>{


    int batchInsert(List<Education> list);

    void delEduByResumeId(Integer resume_id);

    List<Education> getEduByResumeId(Integer resume_id);
}
