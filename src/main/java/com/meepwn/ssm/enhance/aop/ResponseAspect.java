package com.meepwn.ssm.enhance.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.utils.LogUtils;
import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.enhance.factory.json.JSONMapperFactory;
import com.meepwn.ssm.entity.dto.ResponseModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
public class ResponseAspect {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    @Pointcut("execution(* com.meepwn.ssm.controller.*.*(..))")
    public void responseAspectMethod() {
    }

    @Around("responseAspectMethod()")
    public ResponseModel responseModel(ProceedingJoinPoint joinPoint) {
        System.out.println("======= ResponseAspect");

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        ResponseModel responseModel;
        try {
            Object[] args = joinPoint.getArgs();

            // 日志开始
            logBegin(args, request);

            // 执行 Controller 逻辑
            Object value = joinPoint.proceed(args);
            responseModel = ResponseUtils.responseModel(value);

            // 日志结束
            logAfter(responseModel, request);
        } catch (Throwable throwable) {
            throwable.printStackTrace();

            responseModel = ResponseUtils.error(throwable);

            logError(responseModel, request);
        }
        return responseModel;
    }

    /**
     * 打印日志(请求)
     * @param args 请求参数
     * @param request 请求
     */
    private void logBegin(Object[] args, HttpServletRequest request) {
        Object params = args.length > 0 ? args[0] : new Object();
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime); // 线程绑定变量 (该数据只有当前请求的线程可见)

        LogUtils.i("== url ===>>>> {}", request.getRequestURI());
        LogUtils.i("== params ===>>>> {}", toJSONString(params));
    }

    /**
     * 打印日志(响应)
     * @param responseModel 响应报文
     * @param request 请求
     */
    private void logAfter(ResponseModel responseModel, HttpServletRequest request) {
        long beginTime = startTimeThreadLocal.get(); // 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); // 结束时间

        LogUtils.i("== url ===>>>> {}", request.getRequestURI());
        LogUtils.i("== response ===>>>> {}", toJSONString(responseModel));
        LogUtils.i("== network cost time ===>>>> {}", (endTime - beginTime));

        startTimeThreadLocal.remove();
    }

    /**
     * 打印日志(异常)
     * @param responseModel 响应实体
     * @param request 请求
     */
    private void logError(ResponseModel responseModel, HttpServletRequest request) {
        long beginTime = startTimeThreadLocal.get(); // 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); // 结束时间

        LogUtils.e("== url ===>>>> {}", request.getRequestURI());
        LogUtils.e("== exception ===>>>> {}", toJSONString(responseModel));
        LogUtils.i("== network error cost time ===>>>> {}", (endTime - beginTime));

        startTimeThreadLocal.remove();
    }

    /**
     * 将实体转为 JSON 字符串
     * @param object 实体
     * @return JSON 字符串
     */
    private String toJSONString(Object object) {
        ObjectMapper mapper = JSONMapperFactory.newInstance();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
