package com.meepwn.ssm.common.constant.response;

public enum ResponseEnum {

    SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "接口访问成功"),
    FAILURE(ResponseCodeEnum.FAILURE.getRetCode(), "接口访问失败"),
    QUERY_SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "接口查询成功"),
    QUERY_FAILURE(ResponseCodeEnum.FAILURE.getRetCode(), "接口查询失败"),
    QUERY_USER_SUCCESS(ResponseCodeEnum.FAILURE.getRetCode(), "该用户不存在");

    private String retCode;
    private String retMsg;

    ResponseEnum(String retCode) {
        this.retCode = retCode;
    }

    ResponseEnum(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                '}';
    }

}
