package com.milk.job.model.dto;

import com.milk.job.model.pojo.Application;
import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-12 15:18
 */
@Data
public class ApplicationDto extends Application {

    private String userName;

    private String title;

    private String HRName;

    private String companyName;
}
