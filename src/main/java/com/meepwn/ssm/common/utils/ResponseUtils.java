package com.meepwn.ssm.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.factory.bean.BeanFactory;
import com.meepwn.ssm.enhance.factory.json.JSONMapperFactory;
import com.meepwn.ssm.entity.dto.ResponseModel;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ResponseUtils {

    public static final String JSON_CONTENT_TYPE = "application/json";

    /**
     * 响应数据
     *
     * @param response 响应对象
     * @param value    返回结果
     * @param args     可选参数
     * @return 响应数据
     */
    public static ResponseModel responseModel(ServletResponse response, Object value, Object... args) {
        ResponseModel.Builder builder = setResponseInfo(value == null, ResponseEnum.QUERY_USER_SUCCESS);
        ResponseModel responseModel = builder.setData(value).build();

        response.setContentType(JSON_CONTENT_TYPE);

        return responseModel;
    }

    /**
     * 响应数据
     *
     * @param response 响应对象
     * @param list     返回结果
     * @param args     可选参数
     * @return 响应数据
     */
    public static ResponseModel responseModel(ServletResponse response, List list, Object... args) {
        ResponseModel.Builder builder = setResponseInfo(list.size() == 0, ResponseEnum.QUERY_USERS_SUCCESS);
        ResponseModel responseModel;
        if (list.size() != 0) {
            responseModel = builder.setDataList(list).build();
        } else {
            responseModel = builder.build();
        }

        response.setContentType(JSON_CONTENT_TYPE);

        return responseModel;
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
        ResponseModel responseModel = responseModel(response, value, args);
        ObjectMapper mapper = JSONMapperFactory.newInstance();
        return mapper.writeValueAsString(responseModel);
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
        ResponseModel responseModel = responseModel(response, list, args);
        ObjectMapper mapper = JSONMapperFactory.newInstance();
        return mapper.writeValueAsString(responseModel);
    }

    /**
     * @param b           判断条件
     * @param failureEnum 报错枚举
     */
    private static ResponseModel.Builder setResponseInfo(boolean b, ResponseEnum failureEnum) {
        ResponseModel.Builder builder = (ResponseModel.Builder) BeanFactory.newInstance(ResponseModel.Builder.class);
        if (b) {
            return Objects.requireNonNull(builder).setRetCode(failureEnum.getRetCode())
                    .setRetMsg(failureEnum.getRetMsg());
        } else {
            return Objects.requireNonNull(builder).setRetCode(ResponseEnum.QUERY_SUCCESS.getRetCode())
                    .setRetMsg(ResponseEnum.QUERY_SUCCESS.getRetMsg());
        }
    }

}
