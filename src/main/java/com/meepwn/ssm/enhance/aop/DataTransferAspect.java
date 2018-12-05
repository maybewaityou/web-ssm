package com.meepwn.ssm.enhance.aop;

import com.meepwn.ssm.common.utils.JSONUtils;
import com.meepwn.ssm.common.utils.LogUtils;
import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.entity.dto.ResponseModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
public class DataTransferAspect {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    @Pointcut("execution(* com.meepwn.ssm.controller.*.*(..))")
    public void dataTransferAspectMethod() {
    }

    @Around("dataTransferAspectMethod()")
    public ResponseModel responseModel(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        ResponseModel responseModel;
        try {
            Object[] args = joinPoint.getArgs();

            // 请求日志
            requestLog(args, request);

            // 执行 Controller 逻辑
            Object value = joinPoint.proceed(args);
            responseModel = ResponseUtils.responseModel(value);

            // 响应日志
            responseLog(responseModel, request);
        } catch (Throwable throwable) {
            throwable.printStackTrace();

            responseModel = ResponseUtils.error(throwable);

            // 异常日志
            exceptionLog(responseModel, request);
        }
        return responseModel;
    }

    /**
     * 打印日志(请求)
     *
     * @param args    请求参数
     * @param request 请求
     */
    private void requestLog(Object[] args, HttpServletRequest request) {
        Object params = args.length > 0 ? args[0] : new Object();
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime); // 线程绑定变量 (该数据只有当前请求的线程可见)

        LogUtils.i("== url ===>>>> {}", request.getRequestURI());
        LogUtils.i("== params ===>>>> {}", JSONUtils.toJSONString(params));
    }

    /**
     * 打印日志(响应)
     *
     * @param responseModel 响应报文
     * @param request       请求
     */
    private void responseLog(ResponseModel responseModel, HttpServletRequest request) {
        long beginTime = startTimeThreadLocal.get(); // 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); // 结束时间

        LogUtils.i("== url ===>>>> {}", request.getRequestURI());
        LogUtils.i("== response ===>>>> {}", JSONUtils.toJSONString(responseModel));
        LogUtils.i("== network cost time ===>>>> {}", (endTime - beginTime));

        startTimeThreadLocal.remove();
    }

    /**
     * 打印日志(异常)
     *
     * @param responseModel 响应实体
     * @param request       请求
     */
    private void exceptionLog(ResponseModel responseModel, HttpServletRequest request) {
        long beginTime = startTimeThreadLocal.get(); // 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); // 结束时间

        LogUtils.e("== url ===>>>> {}", request.getRequestURI());
        LogUtils.e("== exception ===>>>> {}", JSONUtils.toJSONString(responseModel));
        LogUtils.i("== network error cost time ===>>>> {}", (endTime - beginTime));

        startTimeThreadLocal.remove();
    }

}
