package com.meepwn.ssm.entity.dto;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import lombok.Getter;

/**
 * @author MeePwn
 */
public class ResponseDTO {

    @Getter
    private ResponseEnum successEnum;

    @Getter
    private ResponseEnum failureEnum;

    @Getter
    private Object value;

    private ResponseDTO(Builder builder) {
        this.successEnum = builder.successEnum;
        this.failureEnum = builder.failureEnum;
        this.value = builder.value;
    }

    public static class Builder {
        private ResponseEnum successEnum;
        private ResponseEnum failureEnum;
        private Object value;

        public ResponseDTO.Builder setSuccessEnum(ResponseEnum successEnum) {
            this.successEnum = successEnum;
            return this;
        }

        public ResponseDTO.Builder setFailureEnum(ResponseEnum failureEnum) {
            this.failureEnum = failureEnum;
            return this;
        }

        public ResponseDTO.Builder setValue(Object value) {
            this.value = value;
            return this;
        }

        public ResponseDTO build() {
            return new ResponseDTO(this);
        }

    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "successEnum=" + successEnum +
                ", failureEnum=" + failureEnum +
                ", value=" + value +
                '}';
    }
}
