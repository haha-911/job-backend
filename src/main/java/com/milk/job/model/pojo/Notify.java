package com.milk.job.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


/**
 *   @Description 通知
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@ApiModel(value="com-milk-job-model-pojo-Notify")
@Data
@TableName(value = "`notify`")
public class Notify implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键ID")
    private Integer id;

    /**
     * 消息内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="消息内容")
    private String content;

    @TableField(value = "title")
    @ApiModelProperty(value="消息标题")
    private String title;

    /**
     * 用户名
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户名")
    private Integer userId;

    /**
     * 消息是否已读，默认0代表未读
     */
    @TableField(value = "is_read")
    @ApiModelProperty(value="消息是否已读，默认0代表未读")
    private Integer isRead;

    /**
     * 通知到达的时间
     */
    @TableField(value = "time")
    @ApiModelProperty(value="通知到达的时间")
    private LocalDateTime time;

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