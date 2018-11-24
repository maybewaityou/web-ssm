package proxy;

import com.meepwn.ssm.enhance.factory.proxy.Interceptor;

import java.lang.reflect.Method;

public class UserDaoInterceptor implements Interceptor {

    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("== before ==");
        return false;
    }

    @Override
    public void afterReturning(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("== afterReturning ==");
    }

    @Override
    public void afterThrowing(Object proxy, Object target, Method method, Object[] args, Exception e) {
        System.out.println("== afterThrowing ==");
    }

    @Override
    public Object around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("== around ==");
        return null;
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("== after ==");
    }

}
