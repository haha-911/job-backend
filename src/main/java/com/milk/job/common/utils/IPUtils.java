package com.milk.job.common.utils;

import com.alibaba.fastjson.JSONObject;

import com.milk.job.model.pojo.IpResult;
import com.milk.job.model.pojo.LoginLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-12 15:56
 */

@Slf4j
public class IPUtils {

//    由IP获取地址信息
    public static LoginLog getIpInfo(String ip) {


        try {
            HttpClient httpClient = new HttpClient();

            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

            GetMethod getMethod = new GetMethod("http://api01.aliyun.venuscn.com/ip?ip="+ip);

            // 设置post请求超时时间
            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
            getMethod.addRequestHeader("Content-Type", "application/json");
            getMethod.addRequestHeader("Authorization","APPCODE 74e6f069842740659304788f268006e2");

            httpClient.executeMethod(getMethod);

            String result = getMethod.getResponseBodyAsString();

            log.info("result:{}",result);

            IpResult ipResult = JSONObject.parseObject(result, IpResult.class);

            getMethod.releaseConnection();

            if (ipResult.getRet().equals("200")){
                return ipResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
        return null;

    }

//    获得真实IP
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
    } catch (Exception e) {
        ipAddress="";
    }
    // ipAddress = this.getRequest().getRemoteAddr();

    return ipAddress;
}
//由网关获取真实IP
    public static String getGatwayIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return ip;
    }

}
