package com.meepwn.ssm.common.util;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.factory.bean.BeanFactory;
import com.meepwn.ssm.entity.dto.OutputDTO;
import com.meepwn.ssm.entity.dto.ResponseDTO;

import javax.servlet.ServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @author MeePwn
 */
public class ResponseUtils {

    public static final String JSON_CONTENT_TYPE = "application/json";

    /**
     * 响应数据
     *
     * @param responseDTO 返回结果
     * @param args        可选参数
     * @return 响应数据
     */
    public static OutputDTO outputDTO(ResponseDTO responseDTO, Object... args) {
        Object value = responseDTO.getValue();
        if (value instanceof List) {
            return responseListModel(null, responseDTO, args);
        } else {
            return responseModel(null, responseDTO, args);
        }
    }

    public static OutputDTO error(Throwable exception) {
        OutputDTO.Builder builder = (OutputDTO.Builder) BeanFactory.newInstance(OutputDTO.Builder.class);
        return Objects.requireNonNull(builder)
                .setRetCode(ResponseEnum.EXCEPTION.getRetCode())
                .setRetMsg(ResponseEnum.EXCEPTION.getRetMsg())
                .setException(exception)
                .build();
    }

    /**
     * 响应数据
     *
     * @param response    响应对象
     * @param responseDTO 返回数据
     * @param args        可选参数
     * @return 响应数据
     */
    public static OutputDTO responseModel(ServletResponse response, ResponseDTO responseDTO, Object... args) {
        Object value = responseDTO.getValue();
        OutputDTO.Builder builder = setResponseInfo(value == null, responseDTO.getSuccessEnum(), responseDTO.getFailureEnum());
        OutputDTO outputDTO = builder.setData(value).build();

        if (response != null) {
            response.setContentType(JSON_CONTENT_TYPE);
        }

        return outputDTO;
    }

    /**
     * 响应数据
     *
     * @param response    响应对象
     * @param responseDTO 返回数据
     * @param args        可选参数
     * @return 响应数据
     */
    public static OutputDTO responseListModel(ServletResponse response, ResponseDTO responseDTO, Object... args) {
        List list = (List) responseDTO.getValue();
        boolean isEmpty = list.isEmpty();
        OutputDTO.Builder builder = setResponseInfo(isEmpty, responseDTO.getSuccessEnum(), responseDTO.getFailureEnum());
        OutputDTO outputDTO;
        if (isEmpty) {
            outputDTO = builder.build();
        } else {
            outputDTO = builder.setDataList(list).build();
        }

        if (response != null) {
            response.setContentType(JSON_CONTENT_TYPE);
        }

        return outputDTO;
    }

    /**
     * @param b           判断条件
     * @param failureEnum 报错枚举
     */
    private static OutputDTO.Builder setResponseInfo(boolean b, ResponseEnum failureEnum) {
        return setResponseInfo(b, ResponseEnum.QUERY_SUCCESS, failureEnum);
    }

    /**
     * @param b           判断条件
     * @param successEnum 成功枚举
     * @param failureEnum 报错枚举
     */
    private static OutputDTO.Builder setResponseInfo(boolean b, ResponseEnum successEnum, ResponseEnum failureEnum) {
        OutputDTO.Builder builder = (OutputDTO.Builder) BeanFactory.newInstance(OutputDTO.Builder.class);
        if (b) {
            return Objects.requireNonNull(builder).setRetCode(failureEnum.getRetCode())
                    .setRetMsg(failureEnum.getRetMsg());
        } else {
            return Objects.requireNonNull(builder).setRetCode(successEnum.getRetCode())
                    .setRetMsg(successEnum.getRetMsg());
        }
    }

    private ResponseUtils() {
    }

}
