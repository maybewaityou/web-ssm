package com.meepwn.ssm.entity.dto;

import com.meepwn.ssm.entity.dto.exception.ExceptionWrapper;
import lombok.Getter;

/**
 * @author MeePwn
 * <p>
 * 异常响应
 */
public class ExceptionOutputDTO extends ResponseDTO {

    @Getter
    private ExceptionWrapper exception;

    public ExceptionOutputDTO(Builder builder) {
        retCode = builder.retCode;
        retMsg = builder.retMsg;
        exception = builder.exception;
    }

    public static class Builder {
        private String retCode;
        private String retMsg;
        private ExceptionWrapper exception;

        public Builder setRetCode(String retCode) {
            this.retCode = retCode;
            return this;
        }

        public Builder setRetMsg(String retMsg) {
            this.retMsg = retMsg;
            return this;
        }

        public Builder setException(ExceptionWrapper exception) {
            this.exception = exception;
            return this;
        }

        public ExceptionOutputDTO build() {
            return new ExceptionOutputDTO(this);
        }

    }

    @Override
    public String toString() {
        return "ExceptionOutputDTO{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", exception=" + exception +
                '}';
    }
}