package com.meepwn.ssm.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.factory.bean.BeanFactory;
import com.meepwn.ssm.enhance.factory.json.JsonMapperFactory;
import com.meepwn.ssm.entity.dto.ResponseDTO;

import javax.servlet.ServletResponse;
import java.io.IOException;
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
     * @param value 返回结果
     * @param args  可选参数
     * @return 响应数据
     */
    public static ResponseDTO responseDTO(Object value, Object... args) {
        if (value instanceof List) {
            return responseListModel(null, (List) value, args);
        } else {
            return responseModel(null, value, args);
        }
    }

    public static ResponseDTO error(Throwable exception) {
        ResponseDTO.Builder builder = (ResponseDTO.Builder) BeanFactory.newInstance(ResponseDTO.Builder.class);
        return Objects.requireNonNull(builder)
                .setRetCode(ResponseEnum.EXCEPTION.getRetCode())
                .setRetMsg(ResponseEnum.EXCEPTION.getRetMsg())
                .setException(exception)
                .build();
    }

    /**
     * 响应数据
     *
     * @param response 响应对象
     * @param value    返回结果
     * @param args     可选参数
     * @return 响应数据
     */
    public static ResponseDTO responseModel(ServletResponse response, Object value, Object... args) {
        ResponseDTO.Builder builder = setResponseInfo(value == null, ResponseEnum.QUERY_USER_SUCCESS);
        ResponseDTO responseDTO = builder.setData(value).build();

        if (response != null) {
            response.setContentType(JSON_CONTENT_TYPE);
        }

        return responseDTO;
    }

    /**
     * 响应数据
     *
     * @param response 响应对象
     * @param list     返回结果
     * @param args     可选参数
     * @return 响应数据
     */
    public static ResponseDTO responseListModel(ServletResponse response, List list, Object... args) {
        ResponseDTO.Builder builder = setResponseInfo(list.isEmpty(), ResponseEnum.QUERY_USERS_SUCCESS);
        ResponseDTO responseDTO;
        if (list.isEmpty()) {
            responseDTO = builder.build();
        } else {
            responseDTO = builder.setDataList(list).build();
        }

        if (response != null) {
            response.setContentType(JSON_CONTENT_TYPE);
        }

        return responseDTO;
    }

    /**
     * 响应数据 (String)
     *
     * @param response 响应对象
     * @param value    返回结果
     * @param args     可选参数
     * @return 响应数据
     */
    public static String responseString(ServletResponse response, Object value, Object... args) throws IOException {
        ResponseDTO responseDTO = responseModel(response, value, args);
        ObjectMapper mapper = JsonMapperFactory.newInstance();
        return mapper.writeValueAsString(responseDTO);
    }

    /**
     * 响应数据 (String)
     *
     * @param response 响应对象
     * @param list     返回结果
     * @param args     可选参数
     * @return 响应数据
     */
    public static String responseString(ServletResponse response, List list, Object... args) throws IOException {
        ResponseDTO responseDTO = responseModel(response, list, args);
        ObjectMapper mapper = JsonMapperFactory.newInstance();
        return mapper.writeValueAsString(responseDTO);
    }

    /**
     * @param b           判断条件
     * @param failureEnum 报错枚举
     */
    private static ResponseDTO.Builder setResponseInfo(boolean b, ResponseEnum failureEnum) {
        ResponseDTO.Builder builder = (ResponseDTO.Builder) BeanFactory.newInstance(ResponseDTO.Builder.class);
        if (b) {
            return Objects.requireNonNull(builder).setRetCode(failureEnum.getRetCode())
                    .setRetMsg(failureEnum.getRetMsg());
        } else {
            return Objects.requireNonNull(builder).setRetCode(ResponseEnum.QUERY_SUCCESS.getRetCode())
                    .setRetMsg(ResponseEnum.QUERY_SUCCESS.getRetMsg());
        }
    }

    private ResponseUtils() {
    }

}
