package com.milk.job.common.enums;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 21:49
 */
@Getter
public enum ResultEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    SYS_ERROR(999,"系统异常"),
    ILLEGAL_REQUEST(205, "非法请求"),
    REPEAT_SUBMIT(206, "重复提交"),
    ARGUMENT_VALID_ERROR(210, "参数校验异常"),

    LOGIN_AUTH(208, "未登陆或登录已过期"),
    PERMISSION(209, "没有权限"),
    ACCOUNT_ERROR(214, "账号不正确或密码不正确"),
    ACCOUNT_NULL(215,"账号不存在"),
    USER_TYPE_ERROR(216,"身份识别失败！"),
    ACCOUNT_STOP( 217, "账号已停用"),
    NODE_ERROR( 218, "该节点下有子节点，不可以操作"),
    EMAIL_CODE_ERROR( 219, "邮箱验证码错误！"),
    EMAIL_CODE_EXPIRE( 220, "邮箱验证码已过期！");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
}
