package com.meepwn.ssm.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.factory.bean.BeanFactory;
import com.meepwn.ssm.enhance.factory.json.JSONMapperFactory;
import com.meepwn.ssm.pojo.response.ResponseModel;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ResponseUtils {

    public static final String JSON_CONTENT_TYPE = "application/json";

    public static ResponseModel responseModel(ServletResponse response, Object value, Object... args) throws IOException {
        ResponseModel responseModel = (ResponseModel) BeanFactory.newInstance(ResponseModel.class);
        setResponseInfo(responseModel, value == null, ResponseEnum.QUERY_USER_SUCCESS);
        Objects.requireNonNull(responseModel).setData(value);

        response.setContentType(JSON_CONTENT_TYPE);

        return responseModel;
    }

    public static ResponseModel responseModel(ServletResponse response, List list, Object... args) throws IOException {
        ResponseModel responseModel = (ResponseModel) BeanFactory.newInstance(ResponseModel.class);
        setResponseInfo(responseModel, list.size() == 0, ResponseEnum.QUERY_USERS_SUCCESS);
        if (list.size() != 0) Objects.requireNonNull(responseModel).setDataList(list);

        response.setContentType(JSON_CONTENT_TYPE);

        return responseModel;
    }

    public static String responseString(ServletResponse response, Object value, Object... args) throws IOException {
        ResponseModel responseModel = responseModel(response, value, args);
        ObjectMapper mapper = JSONMapperFactory.newInstance();
        return mapper.writeValueAsString(responseModel);
    }

    /**
     * @param responseModel 响应 Model
     * @param b             判断条件
     * @param failureEnum   报错枚举
     */
    private static void setResponseInfo(ResponseModel responseModel, boolean b, ResponseEnum failureEnum) {
        if (b) {
            Objects.requireNonNull(responseModel).setRetCode(failureEnum.getRetCode());
            responseModel.setRetMsg(failureEnum.getRetMsg());
        } else {
            Objects.requireNonNull(responseModel).setRetCode(ResponseEnum.QUERY_SUCCESS.getRetCode());
            responseModel.setRetMsg(ResponseEnum.QUERY_SUCCESS.getRetMsg());
        }
    }

}
