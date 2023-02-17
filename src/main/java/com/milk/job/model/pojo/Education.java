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

@ApiModel(value = "com-milk-job-model-pojo-Education")
@Data
@TableName(value = "education")
public class Education implements Serializable {
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
     * 学校名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "学校名称")
    private String name;

    /**
     * 专业
     */
    @TableField(value = "major")
    @ApiModelProperty(value = "专业")
    private String major;

    /**
     * 学历（0 高中，1 大学专科，2 大学本科，3 硕士研究生， 4 博士研究生）
     */
    @TableField(value = "diploma")
    @ApiModelProperty(value = "学历（0 高中，1 大学专科，2 大学本科，3 硕士研究生， 4 博士研究生）")
    private Integer diploma;

    /**
     * 描述
     */
    @TableField(value = "descriptions")
    @ApiModelProperty(value = "描述")
    private String descriptions;

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

    private static final long serialVersionUID = 1L;
}