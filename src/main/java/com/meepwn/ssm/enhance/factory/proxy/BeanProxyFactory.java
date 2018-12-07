package com.meepwn.ssm.enhance.factory.proxy;

/**
 * @author MeePwn
 */
public class BeanProxyFactory {

    public static Object newInstance(Class cls, String interceptorClass) throws IllegalAccessException, InstantiationException {
        return BeanProxy.bind(cls.newInstance(), interceptorClass);
    }

    public static Object newInstance(Class cls) throws IllegalAccessException, InstantiationException {
        return BeanProxy.bind(cls.newInstance(), null);
    }

    public static Object newInstance(Object target, String interceptorClass) {
        return BeanProxy.bind(target, interceptorClass);
    }

    public static Object newInstance(Object target) {
        return BeanProxy.bind(target, null);
    }

    private BeanProxyFactory(){
    }

}
