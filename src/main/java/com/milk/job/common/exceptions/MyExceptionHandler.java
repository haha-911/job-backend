package com.milk.job.common.exceptions;

import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 22:03
 */

@ControllerAdvice
@ResponseBody
@Slf4j
public class MyExceptionHandler {

    /*
    * 系统异常
    * */
    @ExceptionHandler(Exception.class)
    public  R systemException(Exception e){

        e.printStackTrace();

        return R.fail(ResultEnum.SYS_ERROR);

    }



    /*
    * 自定义异常
    *
    * */
    @ExceptionHandler(CustomerException.class)
    public R businessException(CustomerException e){

        return R.fail(e.getMsg(),e.getCode());
    }


    /*
    * SQL字段唯一异常
    * */
    @ExceptionHandler(DuplicateKeyException.class)
    public R sqlUniqueException(DuplicateKeyException e){

        if (e.getMessage().contains("Duplicate entry")){
            String[] s = e.getMessage().split("'");
            String msg = s[1]+"已存在！";
            return R.fail(msg);
        }

        return R.fail(ResultEnum.SYS_ERROR);

    }
}
