package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:09
 */

@ApiModel(value = "com-milk-job-model-pojo-Position")
@Data
@TableName(value = "`position`")
public class Position implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 职位名称
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "职位名称")
    private String title;

    /**
     * 职位要求
     */
    @TableField(value = "requirement")
    @ApiModelProperty(value = "职位要求")
    private String requirement;

    /**
     * 招聘人数
     */
    @TableField(value = "quantity")
    @ApiModelProperty(value = "招聘人数")
    private Integer quantity;

    /**
     * 工作地点
     */
    @TableField(value = "city")
    @ApiModelProperty(value = "工作地点")
    private String city;

    /**
     * 最高薪资
     */
    @TableField(value = "salary_up")
    @ApiModelProperty(value = "最高薪资")
    private Integer salaryUp;

    /**
     * 最低薪资
     */
    @TableField(value = "salary_down")
    @ApiModelProperty(value = "最低薪资")
    private Integer salaryDown;

    /**
     * 发布时间
     */
    @TableField(value = "release_date")
    @ApiModelProperty(value = "发布时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM")
    private LocalDateTime releaseDate;

    /**
     * 职位状态
     */
    @TableField(value = "state")
    @ApiModelProperty(value = "职位状态 0 下架 1 正常 2 待审核")
    private Integer state;

    /**
     * 职位浏览量
     */
    @TableField(value = "hits")
    @ApiModelProperty(value = "职位浏览量")
    private Integer hits;

    /**
     * 职位分类id
     */
    @TableField(value = "category_id")
    @ApiModelProperty(value = "职位分类id")
    private Integer categoryId;

    /**
     * HR id
     */
    @TableField(value = "hr_id")
    @ApiModelProperty(value = "HR id")
    private Integer hrId;

    /**
     * 公司id
     */
    @TableField(value = "company_id")
    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
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
    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}