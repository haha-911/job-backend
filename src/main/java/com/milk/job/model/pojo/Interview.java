package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 *   @Description 面试
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@ApiModel(value="com-milk-job-model-pojo-Interview")
@Data
@TableName(value = "interview")
public class Interview implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 简历编号
     */
    @TableField(value = "resume_id")
    @ApiModelProperty(value="简历编号")
    private Integer resumeId;

    /**
     * 用户名
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户名")
    private Integer userId;

    /**
     * 公司名称
     */
    @TableField(value = "company_id")
    @ApiModelProperty(value="公司名称")
    private Integer companyId;

    /**
     * 应聘职位名称
     */
    @TableField(value = "position_id")
    @ApiModelProperty(value="应聘职位名称")
    private Integer positionId;

    /**
     * hr id
     */
    @TableField(value = "hr_id")
    @ApiModelProperty(value="hr id")
    private Integer hrId;

    /**
     * 面试时间
     */
    @TableField(value = "time")
    @ApiModelProperty(value="面试时间")
    private Date time;

    /**
     * 面试地点
     */
    @TableField(value = "address")
    @ApiModelProperty(value="面试地点")
    private String address;

    /**
     * 面试评价
     */
    @TableField(value = "comments")
    @ApiModelProperty(value="面试评价")
    private String comments;

    /**
     * 面试状态（0未开始，1面试通过，2面试不通过）
     */
    @TableField(value = "status")
    @ApiModelProperty(value="面试状态（0未开始，1面试通过，2面试不通过）")
    private Integer status;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time" ,fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    @TableField(value = "is_delete")
    @ApiModelProperty(value="是否删除")
    @TableLogic
    private Integer isDelete;

    /**
     * 面试备注
     */
    @TableField(value = "memo")
    @ApiModelProperty(value="面试备注")
    private String memo;

    private static final long serialVersionUID = 1L;
}