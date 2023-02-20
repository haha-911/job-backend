package com.milk.job.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.CompanyDto;
import com.milk.job.model.pojo.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.CompanyVo;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface CompanyService extends IService<Company>{


    int batchInsert(List<Company> list);

    Page<CompanyDto> pageSel(CompanyVo companyVo);

    void delCompany(Integer id);

    List<Company> companyList();

    void changeState(Integer id, Integer state);

    void batchChangeState(List<Integer> ids);

    void batchDelCompany(List<Integer> ids);

    void addCompany(Company company);
}
