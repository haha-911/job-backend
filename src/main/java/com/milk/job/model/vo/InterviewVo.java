package com.milk.job.model.vo;

import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-08 21:09
 */
@Data
public class InterviewVo extends LimitVo {

    private Integer companyId;

    private Integer positionId;

    private String  state;

    private Integer hrId;


}
