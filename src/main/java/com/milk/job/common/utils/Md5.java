package com.milk.job.common.utils;

import org.springframework.util.DigestUtils;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.HashMap;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-11 17:53
 */
public class Md5 {

    public static String md5Digest(String str){

        String newStr = str+"job_milk";

        return  DigestUtils.md5DigestAsHex(newStr.getBytes());
    }

    public static void main(String[] args) {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1,"sdafasfd");
        if (map.get(1) == null){
            map.put(2,"sdafdsfasfasasfd");
        }


        System.out.println(map);
    }

}
