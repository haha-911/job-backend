package com.milk.job.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.ApplicationDto;
import com.milk.job.model.pojo.Application;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.pojo.Notify;
import com.milk.job.model.vo.ApplicationVo;

import java.util.List;
import java.util.Map;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-12 15:01
 */

public interface ApplicationService extends IService<Application>{


    Page<ApplicationDto> getPage(ApplicationVo applicationVo);

    void batchRemoveById(List<Integer> ids);

    void batchPass(Map<String,Object> map);

    void batchRefuse(List<Integer> ids);

    Page<Map<String, Object>> getApplyByUserId(Integer id,ApplicationVo applicationVo);
}
