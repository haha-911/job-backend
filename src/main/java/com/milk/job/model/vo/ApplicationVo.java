package com.milk.job.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-12 15:14
 */

@Data
public class ApplicationVo extends LimitVo {

    /**
     * 申请状态
     */
    private String  state;

    private String applyTimeStart;

    private String applyTimeEnd;

    /**
     * 简历id
     */
    private String resumeId;

    /**
     * 职位id
     */
    private String positionId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * HR id
     */
    private String hrId;

    /**
     * 公司id
     */
    private String companyId;
}
