package com.meepwn.ssm.common.constant.response;

/**
 * @author MeePwn
 */
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "接口访问成功"),
    /**
     * 失败
     */
    FAILURE(ResponseCodeEnum.FAILURE.getRetCode(), "接口访问失败"),
    /**
     * 查询成功
     */
    QUERY_SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "接口查询成功"),
    /**
     * 该用户不存在
     */
    USER_NOT_EXIST(ResponseCodeEnum.FAILURE.getRetCode(), "该用户不存在"),
    /**
     * 用户信息更新失败
     */
    USER_UPDATE_FAILURE(ResponseCodeEnum.FAILURE.getRetCode(), "用户信息更新失败"),
    /**
     * 用户列表为空
     */
    USER_LIST_IS_EMPTY(ResponseCodeEnum.FAILURE.getRetCode(), "用户列表为空"),
    /**
     * 文件上传成功
     */
    FILE_UPLOAD_SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "文件上传成功"),
    /**
     * 文件上传失败
     */
    FILE_UPLOAD_FAILURE(ResponseCodeEnum.SUCCESS.getRetCode(), "文件上传失败"),
    /**
     * 文件上传成功
     */
    FILE_DOWNLOAD_SUCCESS(ResponseCodeEnum.SUCCESS.getRetCode(), "文件下载成功"),
    /**
     * 文件上传失败
     */
    FILE_DOWNLOAD_FAILURE(ResponseCodeEnum.SUCCESS.getRetCode(), "文件下载失败"),

    /**
     * 接口访问异常
     */
    EXCEPTION(ResponseCodeEnum.EXCEPTION.getRetCode(), "接口请求异常");

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
