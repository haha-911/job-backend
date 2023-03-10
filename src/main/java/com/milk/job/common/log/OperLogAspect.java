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
                    String msg = s[1]+"????????????";
                    operLog.setErrorMsg(msg);
                }else {
                    operLog.setErrorMsg(e.getMessage());
                }
            }

            String className = joinPoint.getTarget().getClass().getName();

            String methodName = joinPoint.getSignature().getName();

            operLog.setMethod(className+"."+methodName);

            // ??????????????????????????????
            getControllerMethodDescription(joinPoint, contrLog, operLog, o);

            log.info("?????????{}",operLog);

            operLogService.save(operLog);


        }catch (Exception ex){

            ex.printStackTrace();
        }




        
    }

    /**
     * ??????????????????????????????????????? ??????Controller?????????
     * @param log     ??????
     * @param operLog ????????????
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperLog operLog, Object jsonResult) throws Exception {
        // ????????????
        operLog.setTitle(log.title());
        // ?????????????????????
        operLog.setOperType(log.businessType().name());
        // ??????????????????request???????????????
        if (log.isSaveRequestData()) {

            String params = argsArrayToString(joinPoint.getArgs());
            // ????????????????????????????????????????????????
            operLog.setOperParam(params);
        }

        // ??????????????????response???????????????
        if (log.isSaveResponseData() && !ObjectUtils.isEmpty(jsonResult)) {
            operLog.setResult(JSON.toJSONString(jsonResult));
        }

    }

    /**
     * ????????????
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
     * ????????????????????????????????????
     *
     * @param o ???????????????
     * @return ??????????????????????????????????????????true???????????????false???
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
