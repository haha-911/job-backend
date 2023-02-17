package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 *   @Description 关注公司
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@ApiModel(value="com-milk-job-model-pojo-Follow")
@Data
@TableName(value = "follow")
public class Follow implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 公司id
     */
    @TableField(value = "company_id")
    @ApiModelProperty(value="公司id")
    private Integer companyId;

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
    @ApiModelProperty(value="")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}