package com.meepwn.ssm.enhance.aop;

import com.meepwn.ssm.common.util.LogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author MeePwn
 */
@Aspect
@Component
public class DaoAspect {

    private static final String TRANSACTIONAL_SYMBOL = "Tx";

    @Pointcut("execution(* com.meepwn.ssm.dao.impl.*DaoImpl.*(..))")
    public void daoAspectJMethod() {
    }

    @Around("daoAspectJMethod()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        // 定义返回值
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        if (methodName.contains(TRANSACTIONAL_SYMBOL)) {
            try {
                // TODO 前置通知, 可开启事务: beginTransaction();
                // 执行方法
                result = joinPoint.proceed(joinPoint.getArgs());
                // TODO 后置通知, 可提交事务: commit();
            } catch (Throwable throwable) {
                // TODO 异常通知, 回滚事务: rollback();
                LogUtils.e("{}", throwable);
            } finally {
                // TODO 最终通知, 释放资源: release();
            }
        } else {
            try {
                // TODO 前置通知
                // 执行方法
                result = joinPoint.proceed(joinPoint.getArgs());
                // TODO 后置通知
            } catch (Throwable throwable) {
                // TODO 异常通知
                LogUtils.e("{}", throwable);
            } finally {
                // TODO 最终通知
            }
        }
        return result;
    }

}
