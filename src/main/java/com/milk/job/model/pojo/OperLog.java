package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-10 22:23
 */

@ApiModel(value="com-milk-job-model-pojo-OperLog")
@Data
@TableName(value = "oper_log")
public class OperLog implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 操作名称
     */
    @TableField(value = "title")
    @ApiModelProperty(value="操作名称")
    private String title;

    /**
     * 操作人
     */
    @TableField(value = "username")
    @ApiModelProperty(value="操作人")
    private String username;

    /**
     * 请求方法
     */
    @TableField(value = "method")
    @ApiModelProperty(value="请求方法")
    private String method;

    /**
     * 操作类型
     */
    @TableField(value = "oper_type")
    @ApiModelProperty(value="操作类型")
    private String operType;

    /**
     * 请求参数
     */
    @TableField(value = "oper_param")
    @ApiModelProperty(value="请求参数")
    private String operParam;

    /**
     * 请求路径
     */
    @TableField(value = "oper_url")
    @ApiModelProperty(value="请求路径")
    private String operUrl;

    /**
     * 请求IP
     */
    @TableField(value = "oper_ip")
    @ApiModelProperty(value="请求IP")
    private String operIp;

    /**
     * 返回结果
     */
    @TableField(value = "result")
    @ApiModelProperty(value="返回结果")
    private String result;

    /**
     * 异常信息
     */
    @TableField(value = "error_msg")
    @ApiModelProperty(value="异常信息")
    private String errorMsg;

    /**
     * 操作状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="操作状态")
    private Integer status;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value="")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="")
    private LocalDateTime updateTime;

    @TableField(value = "is_delete")
    @ApiModelProperty(value="")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}