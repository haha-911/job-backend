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

@ApiModel(value = "com-milk-job-model-pojo-Project")
@Data
@TableName(value = "project")
public class Project implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 简历id
     */
    @TableField(value = "resume_id")
    @ApiModelProperty(value = "简历id")
    private Integer resumeId;

    /**
     * 项目名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "项目名称")
    private String name;

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
     * 项目描述
     */
    @TableField(value = "performance")
    @ApiModelProperty(value = "项目描述")
    private String performance;

    /**
     * 项目背景
     */
    @TableField(value = "background")
    @ApiModelProperty(value = "项目背景")
    private String background;

    private static final long serialVersionUID = 1L;
}