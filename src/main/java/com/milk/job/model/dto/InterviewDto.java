package com.milk.job.model.dto;

import com.milk.job.model.pojo.Interview;
import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-08 21:21
 */
@Data
public class InterviewDto extends Interview {

    private String userName;

    private String title;

    private String HRName;

    private String companyName;

    private String uemail;

    private String utel;

    private String hemail;

    private String htel;
}
