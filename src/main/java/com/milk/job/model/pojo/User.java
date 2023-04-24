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

@ApiModel(value = "com-milk-job-model-pojo-User")
@Data
@TableName(value = "`user`")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 用户名，唯一
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名，唯一")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 头像url
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像url")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 电话
     */
    @TableField(value = "tel")
    @ApiModelProperty(value = "电话")
    private String tel;

    /**
     * 身份
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "身份 1 超级管理员  3HR 4 普通用户")
    private Integer  type;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT )
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除 0 正常 1删除
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value = "逻辑删除 0 正常 1删除")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private Integer companyId;

    @TableField(exist = false)
    private Integer resumeId;

    @TableField(exist = false)
    private String code;

    private static final long serialVersionUID = 1L;
}