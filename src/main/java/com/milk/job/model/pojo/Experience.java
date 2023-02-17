package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:09
 */

@ApiModel(value = "com-milk-job-model-pojo-Experience")
@Data
@TableName(value = "experience")
public class Experience implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 简历ID
     */
    @TableField(value = "resume_id")
    @ApiModelProperty(value = "简历ID")
    private Integer resumeId;

    /**
     * 公司名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "公司名称")
    private String name;

    /**
     * 职位
     */
    @TableField(value = "position")
    @ApiModelProperty(value = "职位")
    private String position;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy.MM.dd",timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy.MM.dd",timezone = "GMT+8")
    private Date endTime;

    /**
     * 工作描述
     */
    @TableField(value = "performance")
    @ApiModelProperty(value = "工作描述")
    private String performance;

    private static final long serialVersionUID = 1L;
}