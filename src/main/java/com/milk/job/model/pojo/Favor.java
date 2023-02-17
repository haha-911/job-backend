package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 *   @Description 收藏职位
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@ApiModel(value="com-milk-job-model-pojo-Favor")
@Data
@TableName(value = "favor")
public class Favor implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 职位id
     */
    @TableField(value = "position_id")
    @ApiModelProperty(value="职位id")
    private Integer positionId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time" ,fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value="删除时间")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}