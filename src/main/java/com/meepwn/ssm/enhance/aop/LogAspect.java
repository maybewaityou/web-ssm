package com.meepwn.ssm.enhance.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.utils.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
public class LogAspect {

    @Pointcut("execution(* com.meepwn.ssm.controller.*.*(..))")
    private void aspectJMethod() {
    }

    @Before("aspectJMethod()")
    public void before(JoinPoint joinPoint) throws JsonProcessingException {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) continue;
            if (arg instanceof HttpServletResponse) continue;
            ObjectMapper mapper = new ObjectMapper();
            String params = mapper.writeValueAsString(arg);
            LogUtils.d("== params ===>>>> {}", params);
            break;
        }
    }

    @AfterReturning(value = "aspectJMethod()", returning = "jsonString")
    public void afterReturning(JoinPoint joinPoint, String jsonString) {
        LogUtils.d("== response ===>>>> {}", jsonString);
    }

}
