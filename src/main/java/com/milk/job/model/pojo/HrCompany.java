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

@ApiModel(value = "com-milk-job-model-pojo-HrCompany")
@Data
@TableName(value = "hr_company")
public class HrCompany implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

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
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @TableField(value = "is_delete")
    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}