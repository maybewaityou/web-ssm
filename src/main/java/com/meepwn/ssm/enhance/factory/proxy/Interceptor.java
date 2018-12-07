package com.meepwn.ssm.enhance.factory.proxy;

import java.lang.reflect.Method;

/**
 * 拦截器
 *
 * @author MeePwn
 */
public interface Interceptor {

    /**
     * 前置通知
     *
     * @param proxy  代理对象
     * @param target 目标对象
     * @param method 代理方法
     * @param args   代理方法参数
     * @return 是否拦截
     */
    boolean before(Object proxy, Object target, Method method, Object[] args);

    /**
     * 后置通知
     *
     * @param proxy  代理对象
     * @param target 目标对象
     * @param method 代理方法
     * @param args   代理方法参数
     */
    void afterReturning(Object proxy, Object target, Method method, Object[] args);

    /**
     * 异常抛出通知
     *
     * @param proxy  代理对象
     * @param target 目标对象
     * @param method 代理方法
     * @param args   代理方法参数
     * @param e      抛出异常
     */
    void afterThrowing(Object proxy, Object target, Method method, Object[] args, Exception e);

    /**
     * 环绕通知
     *
     * @param proxy  代理对象
     * @param target 目标对象
     * @param method 代理方法
     * @param args   代理方法参数
     * @return 方法返回值
     */
    Object around(Object proxy, Object target, Method method, Object[] args);

    /**
     * 最终通知
     *
     * @param proxy  代理对象
     * @param target 目标对象
     * @param method 代理方法
     * @param args   代理方法参数
     */
    void after(Object proxy, Object target, Method method, Object[] args);

}
