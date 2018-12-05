package com.meepwn.ssm.enhance.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DaoAspect {

    @Pointcut("execution(* com.meepwn.ssm.dao.impl.*DaoImpl.*(..))")
    public void daoAspectJMethod() {
    }

    @Around("daoAspectJMethod()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        System.out.println("======= DaoAspect");
        // 定义返回值
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        if (methodName.contains("Tx")) {
            try {
                // TODO 前置通知, 可开启事务: beginTransaction();
                // 执行方法
                result = joinPoint.proceed(joinPoint.getArgs());
                // TODO 后置通知, 可提交事务: commit();
            } catch (Throwable throwable) {
                // TODO 异常通知, 回滚事务: rollback();
                throwable.printStackTrace();
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
                throwable.printStackTrace();
            } finally {
                // TODO 最终通知
            }
        }
        return result;
    }

}
