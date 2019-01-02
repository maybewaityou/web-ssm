package com.meepwn.ssm.enhance.retry;


/**
 * @author MeePwn
 * @param <T> the return type
 */
@FunctionalInterface
public interface BusinessOperation<T> {

    /**
     * 包装操作
     *
     * @return 返回值
     * @throws BusinessException 异常
     */
    T perform() throws BusinessException;

}
