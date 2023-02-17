package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.milk.job.model.dto.HrCompanyDto;
import com.milk.job.model.pojo.Company;
import com.milk.job.system.service.CompanyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.milk.job.system.mapper.HrCompanyMapper;
import com.milk.job.model.pojo.HrCompany;
import com.milk.job.system.service.HrCompanyService;
import org.springframework.transaction.annotation.Transactional;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
@Transactional
public class HrCompanyServiceImpl extends ServiceImpl<HrCompanyMapper, HrCompany> implements HrCompanyService {
    @Resource
    private HrCompanyMapper hrCompanyMapper;

    @Override
    public int batchInsert(List<HrCompany> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<HrCompanyDto> getAll(Integer id) {

        List<HrCompanyDto> hrCompanyDtoList = hrCompanyMapper.getAll(id);
        return hrCompanyDtoList;
    }

    @Override
    public void addHrCompany(Integer userId, Integer companyId) {
        HrCompany hrCompany = new HrCompany();

        hrCompany.setHrId(userId);
        hrCompany.setCompanyId(companyId);

        this.save(hrCompany);
    }

    @Override
    public HrCompany getHrCompanyByUserId(Integer id) {

        LambdaQueryWrapper<HrCompany> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HrCompany::getHrId,id);
        HrCompany hrCompany = this.getOne(queryWrapper);
        return hrCompany;
    }

    @Override
    public void updateHrCompany(Integer hrId,Integer companyId) {

        LambdaUpdateWrapper<HrCompany> queryWrapper = new LambdaUpdateWrapper<>();

        queryWrapper.eq(HrCompany::getHrId,hrId).set(HrCompany::getCompanyId,companyId);
        this.update(queryWrapper);


    }

    @Override
    public HrCompanyDto getHrCompanyName(Integer userId) {

        return hrCompanyMapper.getHrCompanyName(userId);
    }


}
