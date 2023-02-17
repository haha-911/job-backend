package com.milk.job.model.pojo;

import com.milk.job.model.pojo.LoginLog;
import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-12 17:13
 */
@Data
public class IpResult {

    private LoginLog data;

    private String ret;

    private String msg;
}
