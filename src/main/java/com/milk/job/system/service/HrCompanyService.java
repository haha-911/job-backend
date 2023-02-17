package com.milk.job.system.service;

import java.util.List;

import com.milk.job.model.dto.HrCompanyDto;
import com.milk.job.model.pojo.HrCompany;
import com.baomidou.mybatisplus.extension.service.IService;
    
/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface HrCompanyService extends IService<HrCompany>{


    int batchInsert(List<HrCompany> list);

    List<HrCompanyDto> getAll(Integer id);

    void addHrCompany(Integer userId,Integer companyId);

    HrCompany getHrCompanyByUserId(Integer id);

    void updateHrCompany(Integer hrId,Integer companyId);

    HrCompanyDto getHrCompanyName(Integer userId);

}
