package com.meepwn.ssm.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DaoAspect {

    @Before("execution(* com.meepwn.ssm.dao.impl.UserDaoImpl.findAllUsers(..))")
    public void before() {
        System.out.println("== DaoAspect before ==");
    }

}
