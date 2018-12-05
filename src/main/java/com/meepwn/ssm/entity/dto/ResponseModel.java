package com.meepwn.ssm.entity.dto;

import java.util.List;

public class ResponseModel {

    private String retCode;
    private String retMsg;
    private Object data;
    private List dataList;
    private Exception exception;

    public ResponseModel(Builder builder) {
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
        private Exception exception;

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

        public Builder setException(Exception exception) {
            this.exception = exception;
            return this;
        }

        public ResponseModel build() {
            return new ResponseModel(this);
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

    public Exception getException() {
        return exception;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", data=" + data +
                ", dataList=" + dataList +
                ", exception=" + exception +
                '}';
    }
}
