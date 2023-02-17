package com.milk.job.model.vo;

import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-09 22:23
 */
@Data
public class UserVo extends LimitVo{

    private String type; //用户身份
    private String username;

    private String startTime;
    private String endTime;

    private String email;


}
