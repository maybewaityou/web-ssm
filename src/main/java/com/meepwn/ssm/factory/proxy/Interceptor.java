package com.meepwn.ssm.factory.proxy;

import java.lang.reflect.Method;

/**
 * 拦截器
 */
public interface Interceptor {

    /**
     * 前置通知
     */
    public boolean before(Object proxy, Object target, Method method, Object[] args);

    /**
     * 后置通知
     */
    public void afterReturning(Object proxy, Object target, Method method, Object[] args);

    /**
     * 异常抛出通知
     */
    public void afterThrowing(Object proxy, Object target, Method method, Object[] args, Exception e);

    /**
     * 环绕通知
     */
    public Object around(Object proxy, Object target, Method method, Object[] args);

    /**
     * 最终通知
     */
    public void after(Object proxy, Object target, Method method, Object[] args);

}
