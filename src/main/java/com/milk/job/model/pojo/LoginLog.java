package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-12 18:19
 */

@ApiModel(value="com-milk-job-model-pojo-LoginLog")
@Data
@TableName(value = "login_log")
public class LoginLog implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * ip地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="ip地址")
    private String ip;

    /**
     * 网络
     */
    @TableField(value = "isp")
    @ApiModelProperty(value="网络")
    private String isp;

    /**
     * 国家
     */
    @TableField(value = "country")
    @ApiModelProperty(value="国家")
    private String country;

    /**
     * 地区
     */
    @TableField(value = "area")
    @ApiModelProperty(value="地区")
    private String area;

    /**
     * 省份
     */
    @TableField(value = "region")
    @ApiModelProperty(value="省份")
    private String region;

    /**
     * 城市
     */
    @TableField(value = "city")
    @ApiModelProperty(value="城市")
    private String city;

    /**
     * 区县
     */
    @TableField(value = "district")
    @ApiModelProperty(value="区县")
    private String district;

    /**
     * 状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态")
    private Integer status;

    /**
     * 返回信息
     */
    @TableField(value = "msg")
    @ApiModelProperty(value="返回信息")
    private String msg;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value="")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value="逻辑删除")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}