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

    private String nickname;

    private String title;

    private String HrName;

    private String companyName;

    private String email;

    private String tel;
}
