package com.milk.job.model.vo;

import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 17:20
 */
@Data
public class ResumeVo {

    private Integer page;

    private Integer pageSize;
//学历
    private Integer diploma;
//专业
    private String major;
//职位
    private String position;


}
