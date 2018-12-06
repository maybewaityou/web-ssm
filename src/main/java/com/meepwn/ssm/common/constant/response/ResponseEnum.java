package com.meepwn.ssm.common.constant.response;

public enum ResponseEnum {

    SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "接口访问成功"),
    FAILURE(ResponseCodeEnum.FAILURE.getRetCode(), "接口访问失败"),
    QUERY_SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "接口查询成功"),
    QUERY_FAILURE(ResponseCodeEnum.FAILURE.getRetCode(), "接口查询失败"),
    QUERY_USER_SUCCESS(ResponseCodeEnum.FAILURE.getRetCode(), "该用户不存在"),
    QUERY_USERS_SUCCESS(ResponseCodeEnum.FAILURE.getRetCode(), "用户列表为空"),

    EXCEPTION(ResponseCodeEnum.EXCEPTION.getRetCode(), "接口访问异常");

    private String retCode;
    private String retMsg;

    ResponseEnum(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                '}';
    }

}
