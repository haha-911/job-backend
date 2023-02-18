package com.milk.job.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-15 23:33
 */
@Data
public class UserDto {

    private Integer companyId;

    private Integer resumeId;

    private String companyName;

    private Integer id;
    /**
     * 用户名，唯一
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 头像url
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String tel;


    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
