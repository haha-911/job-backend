package com.milk.job.model.vo;

import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 23:26
 */
@Data
public class PositionVo extends LimitVo{

    private String city;

    private String name;

    private String sortCondition;

    private String categoryId;

    private String hrId;

    private String companyId;

    private String minSalary;
    private String maxSalary;

    private String releaseDate;

    private String state;

}
