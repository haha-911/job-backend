package com.milk.job.common.exceptions;

import com.milk.job.common.enums.ResultEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Description 自定义异常
 * @Author Milk
 * @Date 2022-12-10 21:57
 */
@Data
public class CustomerException extends RuntimeException {

    private String msg;

    private Integer code;

    public CustomerException(){}

    public CustomerException(String msg,Integer code){
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public CustomerException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
        this.msg=resultEnum.getMsg();
    }
}
