package com.meepwn.ssm.enhance.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.utils.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Aspect
public class LogAspect {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    @Pointcut("execution(* com.meepwn.ssm.controller.*.*(..))")
    private void aspectJMethod() {
    }

    @Before(value = "aspectJMethod() && args(params, response)", argNames = "params,response")
    public void beforeExecute(Object params, HttpServletResponse response) throws JsonProcessingException {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime); // 线程绑定变量 (该数据只有当前请求的线程可见)
        ObjectMapper mapper = new ObjectMapper();
        String paramsString = mapper.writeValueAsString(params);

        LogUtils.i("== url ===>>>> {}", request.getRequestURI());
        LogUtils.i("== params ===>>>> {}", paramsString);
    }

    @AfterReturning(value = "aspectJMethod()", returning = "jsonString")
    public void afterReturningExecute(JoinPoint joinPoint, String jsonString) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        long beginTime = startTimeThreadLocal.get(); // 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); // 结束时间

        LogUtils.i("== url ===>>>> {}", request.getRequestURI());
        LogUtils.i("== response ===>>>> {}", jsonString);
        LogUtils.i("== network cost time ===>>>> {}", (endTime - beginTime));

        startTimeThreadLocal.remove();
    }

}
