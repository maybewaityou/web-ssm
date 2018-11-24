package com.meepwn.ssm.enhance.factory.bean;

public class BeanFactory {

    public static Object newInstance(Class cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
