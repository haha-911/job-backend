package com.milk.job.model.dto;

import com.milk.job.model.pojo.Company;
import com.milk.job.model.pojo.Position;
import lombok.Data;

import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-02 11:59
 */
@Data
public class CompanyDto extends Company {

    private Integer positionCount;

    private Integer hrCount;
}
