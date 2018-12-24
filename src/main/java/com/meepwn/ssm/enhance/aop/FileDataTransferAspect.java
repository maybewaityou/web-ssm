package com.meepwn.ssm.enhance.aop;

import com.meepwn.ssm.common.util.LogUtils;
import com.meepwn.ssm.common.util.ResponseUtils;
import com.meepwn.ssm.enhance.aop.handler.ProceedHandler;
import com.meepwn.ssm.enhance.aop.trace.TracePrinter;
import com.meepwn.ssm.entity.dto.OutputDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author MeePwn
 */
@Aspect
@Component
public class FileDataTransferAspect {

    @Resource
    private TracePrinter tracePrinter;


    /**
     * 注册切面
     */
    @Pointcut("execution(* com.meepwn.ssm.controller.*.*Upload(..))")
    public void fileDataTransferAspectMethod() {
    }

    @Around("fileDataTransferAspectMethod()")
    public OutputDTO responseModel(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        OutputDTO outputDTO;
        Object[] args = joinPoint.getArgs();
        try {
            // 请求日志
            tracePrinter.requestLog(args, request);

            // 执行 Controller 逻辑
            outputDTO = ProceedHandler.proceed(joinPoint, args);

            // 响应日志
            tracePrinter.responseLog(args, outputDTO, request);
        } catch (Throwable throwable) {
            LogUtils.e("{}", throwable);
            outputDTO = ResponseUtils.error(throwable);

            // 异常日志
            tracePrinter.exceptionLog(args, outputDTO, request);
        }
        return outputDTO;
    }

}
