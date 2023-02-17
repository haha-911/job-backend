package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:09
 */

@ApiModel(value = "com-milk-job-model-pojo-Resume")
@Data
@TableName(value = "resume")
public class Resume implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 专业技能
     */
    @TableField(value = "ability")
    @ApiModelProperty(value = "专业技能")
    private String ability;

    /**
     * 英语等级
     */
    @TableField(value = "english")
    @ApiModelProperty(value = "英语等级")
    private String english;

    /**
     * 性别（0男 ，1 女）
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别（0男 ，1 女）")
    private Integer sex;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 居住地
     */
    @TableField(value = "home")
    @ApiModelProperty(value = "居住地")
    private String home;

    /**
     * 个人总结
     */
    @TableField(value = "personal_summary")
    @ApiModelProperty(value = "个人总结")
    private String personalSummary;

    /**
     * 求职意向
     */
    @TableField(value = "job_intention")
    @ApiModelProperty(value = "求职意向")
    private String jobIntention;

    /**
     * 创建时间
     */
    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time" ,fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value = "删除时间")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}