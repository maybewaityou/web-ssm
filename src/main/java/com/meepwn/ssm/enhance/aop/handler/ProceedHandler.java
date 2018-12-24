package com.meepwn.ssm.enhance.aop.handler;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.common.util.ResponseUtils;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import com.meepwn.ssm.entity.dto.OutputDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import java.lang.reflect.Method;

/**
 * @author MeePwn
 */
@Component
public class ProceedHandler {

    /**
     * 执行 Controller 逻辑
     *
     * @param joinPoint 切面
     * @param args      参数
     * @return 返回参数
     * @throws Throwable 执行逻辑中可能抛出的异常
     */
    public static Object proceedDownload(ProceedingJoinPoint joinPoint, Object[] args) throws Throwable {
        return joinPoint.proceed(args);
    }

        /**
         * 执行 Controller 逻辑
         *
         * @param joinPoint 切面
         * @param args      参数
         * @return 返回参数
         * @throws Throwable 执行逻辑中可能抛出的异常
         */
    public static OutputDTO proceed(ProceedingJoinPoint joinPoint, Object[] args) throws Throwable {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            joinPoint.proceed(args);
            return null;
        }

        OutputDTO outputDTO = null;
        Object value = joinPoint.proceed(args);
        Signature signature = joinPoint.getSignature();
        Class cls = joinPoint.getTarget().getClass();
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(signature.getName())) {
                ResponseAdvice annotation = method.getAnnotation(ResponseAdvice.class);
                ResponseEnum successEnum = annotation != null ? annotation.success() : ResponseEnum.SUCCESS;
                ResponseEnum failureEnum = annotation != null ? annotation.failure() : ResponseEnum.FAILURE;
                outputDTO = ResponseUtils.outputDTO(value, successEnum, failureEnum);
            }
        }
        return outputDTO;
    }

    private ProceedHandler() {
    }

}
