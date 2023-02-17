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

@ApiModel(value = "com-milk-job-model-pojo-Category")
@Data
@TableName(value = "category")
public class Category implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 分类名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 分类描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "分类描述")
    private String description;

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