package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milk.job.model.dto.HrCompanyDto;
import com.milk.job.model.pojo.HrCompany;
import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface HrCompanyMapper extends BaseMapper<HrCompany> {
    int batchInsert(@Param("list") List<HrCompany> list);

    List<HrCompanyDto> getAll(Integer id);

    HrCompanyDto getHrCompanyName(@Param("id") Integer userId);

    Integer selectCount(@Param("id") Integer id);
}