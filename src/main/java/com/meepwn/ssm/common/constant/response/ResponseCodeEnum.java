package com.meepwn.ssm.common.constant.response;

/**
 * @author MeePwn
 */
public enum ResponseCodeEnum {

    /* 成功返回码 */
    SUCCESS("000000"),
    /* 失败返回码 */
    FAILURE("111111"),
    /* 异常返回码 */
    EXCEPTION("222222");

    private String retCode;

    ResponseCodeEnum(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    @Override
    public String toString() {
        return "ResponseCodeEnum{" +
                "retCode='" + retCode + '\'' +
                '}';
    }
}
