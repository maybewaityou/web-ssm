package com.meepwn.ssm.enhance.exception;

import com.meepwn.ssm.common.utils.JSONUtils;
import com.meepwn.ssm.common.utils.LogUtils;
import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.entity.dto.ResponseModel;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            ResponseModel responseModel = ResponseUtils.error(ex);
            String jsonString = JSONUtils.toJSONString(responseModel);

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
