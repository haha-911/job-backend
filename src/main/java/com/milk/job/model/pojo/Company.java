package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.extern.java.Log;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:09
 */

@ApiModel(value = "com-milk-job-model-pojo-Company")
@Data
@TableName(value = "company")
public class Company implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 公司名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "公司名称")
    private String name;

    /**
     * 公司外文名
     */
    @TableField(value = "foreign_name")
    @ApiModelProperty(value = "公司外文名")
    private String foreignName;

    /**
     * 总部地点
     */
    @TableField(value = "city")
    @ApiModelProperty(value = "总部地点")
    private String city;

    /**
     * 公司
     */
    @TableField(value = "logo")
    @ApiModelProperty(value = "公司")
    private String logo;

    /**
     * 公司使命
     */
    @TableField(value = "mission")
    @ApiModelProperty(value = "公司使命")
    private String mission;

    /**
     * 公司简介
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "公司简介")
    private String description;

    /**
     * 公司状态
     */
    @TableField(value = "state")
    @ApiModelProperty(value = "公司状态")
    private Integer state;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value = "删除时间")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private Integer hrId;

    private static final long serialVersionUID = 1L;
}