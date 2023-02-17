package com.milk.job.common;

import com.milk.job.common.enums.ResultEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 通用结果返回类
 * @Author Milk
 * @Date 2022-12-09 22:03
 */
@Data
public class R {


    private boolean success;

    private Integer code;

    private String msg;

    private Object data;

    private Map<String ,Object> map = new HashMap<>();


    public static R success(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(200);
        r.setMsg("ok");
        return r;
    }
    public static R success(String msg){
        R r =R.success();
        r.setMsg(msg);
        return r;
    }

    public static R success(String msg,Object data){
        R r = R.success(msg);
        r.setData(data);
        return r;
    }
    public static R success(Object data){
        R r = R.success();
        r.setData(data);
        return r;
    }

    public static R fail(){
        R r = new R();
        r.setSuccess(false);
        return r;
    }

    public static R fail(String msg){
        R r = R.fail();
        r.setCode(500);
        r.setMsg(msg);
        return r;
    }

    public static R fail(String msg ,Integer code){
        R r = R.fail(msg);

        r.setCode(code);

        return r;
    }

    public static R fail(ResultEnum resultEnum){
        R r = R.fail();
        r.setCode(resultEnum.getCode());
        r.setMsg(resultEnum.getMsg());
        return r;
    }

    public R add(String key,Object value){
        map.put(key,value);
        return this;
    }
}
