package com.meepwn.ssm.entity.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author MeePwn
 */
public class OutputDTO {

    @Getter
    private String retCode;

    @Getter
    private String retMsg;

    @Getter
    private Object data;

    @Getter
    private List dataList;
    
    @Getter
    private Throwable exception;

    public OutputDTO(Builder builder) {
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

        public OutputDTO build() {
            return new OutputDTO(this);
        }

    }

    @Override
    public String toString() {
        return "OutputDTO{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", data=" + data +
                ", dataList=" + dataList +
                ", exception=" + exception +
                '}';
    }
}
