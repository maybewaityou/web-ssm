package com.meepwn.ssm.enhance.factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author MeePwn
 */
public class BeanProxy implements InvocationHandler {

    private Object target;
    private String interceptorClass;

    private BeanProxy(Object target, String interceptorClass) {
        this.target = target;
        this.interceptorClass = interceptorClass;
    }

    static Object bind(Object target, String interceptorClass) {
        Class cls = target.getClass();
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new BeanProxy(target, interceptorClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (interceptorClass == null || "".equals(interceptorClass)) {
            return method.invoke(target, args);
        }
        Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
        Object result = null;
        try {
            if (interceptor.before(proxy, target, method, args)) {
                result = interceptor.around(proxy, target, method, args);
            } else {
                result = method.invoke(target, args);
            }
            interceptor.afterReturning(proxy, target, method, args);
        } catch (Exception e) {
            interceptor.afterThrowing(proxy, target, method, args, e);
        } finally {
            interceptor.after(proxy, target, method, args);
        }

        return result;
    }

}
