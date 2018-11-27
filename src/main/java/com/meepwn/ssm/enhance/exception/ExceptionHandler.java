package com.meepwn.ssm.enhance.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.common.utils.LogUtils;
import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.enhance.factory.bean.BeanFactory;
import com.meepwn.ssm.enhance.factory.json.JSONMapperFactory;
import com.meepwn.ssm.pojo.response.ResponseModel;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ExceptionHandler extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            ResponseModel.Builder builder = (ResponseModel.Builder) BeanFactory.newInstance(ResponseModel.Builder.class);
            ResponseModel responseModel = Objects.requireNonNull(builder)
                    .setRetCode(ResponseEnum.EXCEPTION.getRetCode())
                    .setRetMsg(ResponseEnum.EXCEPTION.getRetMsg())
                    .setException(ex)
                    .build();
            System.out.println("=======");
            ObjectMapper mapper = JSONMapperFactory.newInstance();
            String jsonString = mapper.writeValueAsString(responseModel);

            LogUtils.e("== url ===>>>> {}", request.getRequestURI());
            LogUtils.e("== exception ===>>>> {}", jsonString);

            response.setContentType(ResponseUtils.JSON_CONTENT_TYPE);
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
