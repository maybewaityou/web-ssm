package com.meepwn.ssm.entity.dto;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import lombok.Getter;

/**
 * @author MeePwn
 * <p>
 * 响应业务数据
 */
public class DataDTO {

    @Getter
    private ResponseEnum successEnum;

    @Getter
    private ResponseEnum failureEnum;

    @Getter
    private Object value;

    private DataDTO(Builder builder) {
        this.successEnum = builder.successEnum;
        this.failureEnum = builder.failureEnum;
        this.value = builder.value;
    }

    public static class Builder {
        private ResponseEnum successEnum;
        private ResponseEnum failureEnum;
        private Object value;

        public DataDTO.Builder setSuccessEnum(ResponseEnum successEnum) {
            this.successEnum = successEnum;
            return this;
        }

        public DataDTO.Builder setFailureEnum(ResponseEnum failureEnum) {
            this.failureEnum = failureEnum;
            return this;
        }

        public DataDTO.Builder setValue(Object value) {
            this.value = value;
            return this;
        }

        public DataDTO build() {
            return new DataDTO(this);
        }

    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "successEnum=" + successEnum +
                ", failureEnum=" + failureEnum +
                ", value=" + value +
                '}';
    }
}
