package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.pojo.Company;
import java.util.List;

import com.milk.job.model.vo.CompanyVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:09
 */

public interface CompanyMapper extends BaseMapper<Company> {

    int batchInsert(@Param("list") List<Company> list);

    Page<Company> pageSel(Page<Company> page, @Param("companyVo") CompanyVo companyVo);

    void batchChangeState(@Param("ids") List<Integer> ids);

    List<Company> getHotCompany();

}