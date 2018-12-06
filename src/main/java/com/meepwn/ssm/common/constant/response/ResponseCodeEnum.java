package com.meepwn.ssm.common.constant.response;

public enum ResponseCodeEnum {

    SUCCESS("000000"),
    FAILURE("111111"),
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
