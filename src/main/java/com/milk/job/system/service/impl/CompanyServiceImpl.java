package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.dto.CompanyDto;
import com.milk.job.model.pojo.Position;
import com.milk.job.model.vo.CompanyVo;
import com.milk.job.system.mapper.HrCompanyMapper;
import com.milk.job.system.mapper.PositionMapper;
import com.milk.job.system.mapper.UserMapper;
import com.milk.job.system.service.HrCompanyService;
import com.milk.job.system.service.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import com.milk.job.model.pojo.Company;
import com.milk.job.system.mapper.CompanyMapper;
import com.milk.job.system.service.CompanyService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService{

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private PositionMapper positionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private HrCompanyMapper hrCompanyMapper;

    @Override
    public int batchInsert(List<Company> list) {

        return companyMapper.batchInsert(list);
    }

    @Override
    public Page<CompanyDto> pageSel(CompanyVo companyVo) {
        Page<Company> page = new Page<>(companyVo.getPage(), companyVo.getPageSize());
        Page<Company> pageInfo = companyMapper.pageSel(page, companyVo);
        Page<CompanyDto> companyDtoPage = new Page<>();
        BeanUtils.copyProperties(pageInfo,companyDtoPage,"records");
        List<Company> companyList = pageInfo.getRecords();
        List<CompanyDto> companyDtoList = new ArrayList<>();

        for (Company company : companyList) {
            CompanyDto companyDto = new CompanyDto();
            BeanUtils.copyProperties(company,companyDto);
            Integer id = company.getId();
            Integer positionCount = positionMapper.selectCountByCompanyId(id);
            Integer hrCount = hrCompanyMapper.selectCount(id);
            companyDto.setPositionCount(positionCount);
            companyDto.setHrCount(hrCount);
            companyDtoList.add(companyDto);

        }
        companyDtoPage.setRecords(companyDtoList);

        return companyDtoPage;

    }

    @Override
    public void delCompany(Integer id) {

        if (id == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        int count = positionMapper.selectCountByCompanyId(id);

        if (count>0){
            throw new CustomerException("公司有正在招聘的职位，暂不能删除！",500);
        }

        this.removeById(id);


    }

    @Override
    public List<Company> companyList() {
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Company::getState,1);
        List<Company> companyList = this.list(queryWrapper);
        List<Company> companys = new ArrayList<>();
        for (Company company : companyList) {
            Company company1 = new Company();
            company1.setId(company.getId());
            company1.setName(company.getName());
            companys.add(company1);
        }
        return companys;
    }

    @Override
    public void changeState(Integer id, Integer state) {
        if (id ==null && state == null){
           throw new  CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        LambdaUpdateWrapper<Company> queryWrapper = new LambdaUpdateWrapper<>();
         queryWrapper.eq(Company::getId,id).eq(Company::getState,state);
         if (state == 1){
             queryWrapper.set(Company::getState,0);
         }
         if (state == 0){
             queryWrapper.set(Company::getState,1);
         }
         if (state == 2){
             queryWrapper.set(Company::getState,1);
         }
         this.update(queryWrapper);
    }

    @Override
    public void batchChangeState(List<Integer> ids) {
        if (ids ==null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        companyMapper.batchChangeState(ids);
    }

    @Override
    public void batchDelCompany(List<Integer> ids) {
        for (Integer id : ids) {
            this.delCompany(id);
        }
    }
}
