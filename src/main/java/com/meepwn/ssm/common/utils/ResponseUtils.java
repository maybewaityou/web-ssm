package com.meepwn.ssm.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.factory.bean.BeanFactory;
import com.meepwn.ssm.pojo.response.ResponseModel;

import javax.servlet.ServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static final String JSON_CONTENT_TYPE = "application/json";

    public static String responseJSON(ServletResponse response, Object value, Object... args) throws IOException {
        ResponseModel responseModel = (ResponseModel) BeanFactory.newInstance(ResponseModel.class);
        if (value == null) {
            responseModel.setRetCode(ResponseEnum.QUERY_USER_SUCCESS.getRetCode());
            responseModel.setRetMsg(ResponseEnum.QUERY_USER_SUCCESS.getRetMsg());
        } else {
            responseModel.setRetCode(ResponseEnum.QUERY_SUCCESS.getRetCode());
            responseModel.setRetMsg(ResponseEnum.QUERY_SUCCESS.getRetMsg());
        }
        responseModel.setData(value);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(responseModel);

        response.setContentType(JSON_CONTENT_TYPE);
        response.getWriter().write(jsonString);
        response.getWriter().close();

        return jsonString;
    }

}
