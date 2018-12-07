package com.meepwn.ssm.enhance.aop;

import com.meepwn.ssm.common.util.JsonUtils;
import com.meepwn.ssm.common.util.LogUtils;
import com.meepwn.ssm.common.util.ResponseUtils;
import com.meepwn.ssm.enhance.exception.ParamsPreparedException;
import com.meepwn.ssm.entity.dto.ResponseDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author MeePwn
 */
@Aspect
@Component
public class DataTransferAspect {

    private static final ThreadLocal<Long> START_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal StartTime");
    private static final String URL_EL_STRING = "== url ===>>>> {}";
    private static final String PARAMS_EL_STRING = "== params ===>>>> {}";
    private static final String RESPONSE_EL_STRING = "== response ===>>>> {}";
    private static final String EXCEPTION_EL_STRING = "== exception ===>>>> {}";
    private static final String TIME_EL_STRING = "== network cost time ===>>>> {}";

    /**
     * 注册切面
     */
    @Pointcut("execution(* com.meepwn.ssm.controller.*.*(..))")
    public void dataTransferAspectMethod() {
    }

    @Around("dataTransferAspectMethod()")
    public ResponseDTO responseModel(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        ResponseDTO responseDTO;
        Object[] args = joinPoint.getArgs();
        try {
            // 请求日志
            requestLog(args, request);

            // 校验请求参数
            validateParams(args);

            // 执行 Controller 逻辑
            Object value = joinPoint.proceed(args);
            responseDTO = ResponseUtils.responseDTO(value);

            // 响应日志
            responseLog(args, responseDTO, request);
        } catch (Throwable throwable) {
            LogUtils.e("{}", throwable);

            responseDTO = ResponseUtils.error(throwable);

            // 异常日志
            exceptionLog(args, responseDTO, request);
        }
        return responseDTO;
    }

    /**
     * 校验请求参数
     *
     * @param args 请求参数
     */
    private void validateParams(Object[] args) {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            return;
        }

        if (args.length > 1 && args[1] instanceof Errors) {
            Errors errors = (Errors) args[1];
            if (errors.hasErrors()) {
                FieldError error = errors.getFieldError();
                if (error != null) {
                    throw new ParamsPreparedException("请求参数错误: " + error.getField() + " " + error.getDefaultMessage());
                } else {
                    throw new ParamsPreparedException("请求参数错误");
                }
            }
        }
    }

    /**
     * 打印日志(请求)
     *
     * @param args    请求参数
     * @param request 请求
     */
    private void requestLog(Object[] args, HttpServletRequest request) {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            return;
        }

        Object params = args.length > 0 ? args[0] : new Object();
        long startTime = System.currentTimeMillis();
        // 线程绑定变量 (该数据只有当前请求的线程可见)
        START_TIME_THREAD_LOCAL.set(startTime);

        LogUtils.i(URL_EL_STRING, request.getRequestURI());
        LogUtils.i(PARAMS_EL_STRING, JsonUtils.toJSONString(params));
    }

    /**
     * 打印日志(响应)
     *
     * @param args        请求参数
     * @param responseDTO 响应报文
     * @param request     请求
     */
    private void responseLog(Object[] args, ResponseDTO responseDTO, HttpServletRequest request) {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            return;
        }

        // 得到线程绑定的局部变量（开始时间）
        long beginTime = START_TIME_THREAD_LOCAL.get();
        // 结束时间
        long endTime = System.currentTimeMillis();

        LogUtils.i(URL_EL_STRING, request.getRequestURI());
        LogUtils.i(RESPONSE_EL_STRING, JsonUtils.toJSONString(responseDTO));
        LogUtils.i(TIME_EL_STRING, (endTime - beginTime));

        START_TIME_THREAD_LOCAL.remove();
    }

    /**
     * 打印日志(异常)
     *
     * @param args        请求参数
     * @param responseDTO 响应实体
     * @param request     请求
     */
    private void exceptionLog(Object[] args, ResponseDTO responseDTO, HttpServletRequest request) {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            return;
        }

        // 得到线程绑定的局部变量（开始时间）
        long beginTime = START_TIME_THREAD_LOCAL.get();
        // 结束时间
        long endTime = System.currentTimeMillis();

        LogUtils.e(URL_EL_STRING, request.getRequestURI());
        LogUtils.e(EXCEPTION_EL_STRING, JsonUtils.toJSONString(responseDTO));
        LogUtils.i(TIME_EL_STRING, (endTime - beginTime));

        START_TIME_THREAD_LOCAL.remove();
    }

}
