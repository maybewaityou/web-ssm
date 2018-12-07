package com.meepwn.ssm.entity.dto;

import java.util.List;

/**
 * @author MeePwn
 */
public class ResponseDTO {

    private String retCode;
    private String retMsg;
    private Object data;
    private List dataList;
    private Throwable exception;

    public ResponseDTO(Builder builder) {
        retCode = builder.retCode;
        retMsg = builder.retMsg;
        data = builder.data;
        dataList = builder.dataList;
        exception = builder.exception;
    }

    public static class Builder {
        private String retCode;
        private String retMsg;
        private Object data;
        private List dataList;
        private Throwable exception;

        public Builder setRetCode(String retCode) {
            this.retCode = retCode;
            return this;
        }

        public Builder setRetMsg(String retMsg) {
            this.retMsg = retMsg;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public Builder setDataList(List dataList) {
            this.dataList = dataList;
            return this;
        }

        public Builder setException(Throwable exception) {
            this.exception = exception;
            return this;
        }

        public ResponseDTO build() {
            return new ResponseDTO(this);
        }

    }

    public String getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public Object getData() {
        return data;
    }

    public List getDataList() {
        return dataList;
    }

    public Throwable getException() {
        return exception;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", data=" + data +
                ", dataList=" + dataList +
                ", exception=" + exception +
                '}';
    }
}
