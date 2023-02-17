package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-12 15:01
 */

@ApiModel(value="com-milk-job-model-pojo-Application")
@Data
@TableName(value = "`application`")
public class Application implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 申请状态
     */
    @TableField(value = "state")
    @ApiModelProperty(value="申请状态 0 拒绝 1 通过 2 审核中")
    private Integer state;

    /**
     * 申请时间
     */
    @TableField(value = "apply_time")
    @ApiModelProperty(value="申请时间")
    private LocalDateTime applyTime;

    /**
     * 简历id
     */
    @TableField(value = "resume_id")
    @ApiModelProperty(value="简历id")
    private Integer resumeId;

    /**
     * 职位id
     */
    @TableField(value = "position_id")
    @ApiModelProperty(value="职位id")
    private Integer positionId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * HR id
     */
    @TableField(value = "hr_id")
    @ApiModelProperty(value="HR id")
    private Integer hrId;

    /**
     * 公司id
     */
    @TableField(value = "company_id")
    @ApiModelProperty(value="公司id")
    private Integer companyId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value="是否删除")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}