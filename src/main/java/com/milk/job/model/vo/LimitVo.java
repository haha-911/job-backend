package com.milk.job.model.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-11 14:38
 */

@Data
public class LimitVo {

    private Integer page;
    private Integer pageSize;
}
