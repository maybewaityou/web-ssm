package com.meepwn.ssm.enhance.factory.bean;

import com.meepwn.ssm.common.util.LogUtils;

/**
 * @author MeePwn
 */
public class BeanFactory {

    public static Object newInstance(Class cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LogUtils.e("{}", e);
        }
        return null;
    }

    private BeanFactory() {
    }

}
