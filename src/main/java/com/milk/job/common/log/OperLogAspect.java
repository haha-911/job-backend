package com.milk.job.common.log;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.milk.job.common.R;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.common.utils.IPUtils;
import com.milk.job.common.utils.RequestUtils;
import com.milk.job.common.utils.TokenUtils;
import com.milk.job.model.pojo.OperLog;
import com.milk.job.system.service.OperLogService;
import io.lettuce.core.AclSetuserArgs;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-10 22:36
 */
@Slf4j
@Aspect
@Component
public class OperLogAspect {

    @Resource
    private OperLogService operLogService;


    @AfterReturning(value = "@annotation(contrLog)",returning = "jsonResult")
    public void doAfterReturn(JoinPoint joinPoint,Log contrLog,Object jsonResult){

        handelLog(joinPoint,contrLog,jsonResult,null);

    }


    @AfterThrowing(value = "@annotation(contrLog)",throwing = "e")
    public void doAfterReturn(JoinPoint joinPoint, Log contrLog, Exception e){

        handelLog(joinPoint,contrLog,null,e);

    }

    private void handelLog(JoinPoint joinPoint, Log contrLog, Object o, Exception e) {

        try {
            HttpServletRequest request = RequestUtils.getRequest();

            String token = request.getHeader("token");

            String username = TokenUtils.getUsername(token);

            String ipAddress = IPUtils.getIpAddress(request);

            String requestURI = request.getRequestURI();

            OperLog operLog = new OperLog();

            operLog.setStatus(1);
            operLog.setUsername(username);
            operLog.setOperIp(ipAddress);
            operLog.setOperUrl(requestURI);

            if(e != null){
                operLog.setStatus(0);

                if (e.getMessage().contains("Duplicate entry")){
                    String[] s = e.getMessage().split("'");
                    String msg = s[1]+"已存在！";
                    operLog.setErrorMsg(msg);
                }else {
                    operLog.setErrorMsg(e.getMessage());
                }
            }

            String className = joinPoint.getTarget().getClass().getName();

            String methodName = joinPoint.getSignature().getName();

            operLog.setMethod(className+"."+methodName);

            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, contrLog, operLog, o);

            log.info("数据：{}",operLog);

            operLogService.save(operLog);


        }catch (Exception ex){

            ex.printStackTrace();
        }




        
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperLog operLog, Object jsonResult) throws Exception {
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperType(log.businessType().name());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {

            String params = argsArrayToString(joinPoint.getArgs());
            // 获取参数的信息，传入到数据库中。
            operLog.setOperParam(params);
        }

        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && !ObjectUtils.isEmpty(jsonResult)) {
            operLog.setResult(JSON.toJSONString(jsonResult));
        }

    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (!StringUtils.isEmpty(o.toString()) && !isFilterObject(o)) {
                    try {
                        Object jsonObj = JSON.toJSON(o);
                        params += jsonObj.toString() + " ";
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
