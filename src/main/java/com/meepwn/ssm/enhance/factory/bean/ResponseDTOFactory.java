package com.meepwn.ssm.enhance.factory.bean;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.common.util.ResponseUtils;
import com.meepwn.ssm.entity.dto.OutputDTO;
import com.meepwn.ssm.entity.dto.ResponseDTO;

import java.util.Objects;

/**
 * @author MeePwn
 */
public class ResponseDTOFactory {

    /**
     * 组装返回数据
     *
     * @param value 返回数据
     * @param failureEnum 失败枚举
     * @return 返回数据
     */
    public static OutputDTO newInstance(Object value, ResponseEnum failureEnum) {
        return newInstance(value, ResponseEnum.SUCCESS, failureEnum);
    }

    /**
     * 组装返回数据
     *
     * @param value 返回数据
     * @param successEnum 成功枚举
     * @param failureEnum 失败枚举
     * @return 返回数据
     */
    public static OutputDTO newInstance(Object value, ResponseEnum successEnum, ResponseEnum failureEnum) {
        ResponseDTO.Builder builder = (ResponseDTO.Builder) BeanFactory.newInstance(ResponseDTO.Builder.class);
        ResponseDTO responseDTO = Objects.requireNonNull(builder)
                .setSuccessEnum(successEnum)
                .setFailureEnum(failureEnum)
                .setValue(value)
                .build();
        return ResponseUtils.outputDTO(responseDTO);
    }

    private ResponseDTOFactory() {
    }

}
