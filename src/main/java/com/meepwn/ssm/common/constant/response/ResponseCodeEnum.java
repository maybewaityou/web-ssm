package com.meepwn.ssm.common.constant.response;

public enum ResponseCodeEnum {

    SUCCESS("000000"),
    FAILURE("111111");

    private String retCode;

    ResponseCodeEnum(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    @Override
    public String toString() {
        return "ResponseCodeEnum{" +
                "retCode='" + retCode + '\'' +
                '}';
    }
}
