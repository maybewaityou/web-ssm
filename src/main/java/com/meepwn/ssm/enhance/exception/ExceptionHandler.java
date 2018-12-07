package com.meepwn.ssm.enhance.exception;

import com.meepwn.ssm.common.util.JSONUtils;
import com.meepwn.ssm.common.util.LogUtils;
import com.meepwn.ssm.common.util.ResponseUtils;
import com.meepwn.ssm.entity.dto.ResponseDTO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MeePwn
 */
public class ExceptionHandler extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            ResponseDTO responseDTO = ResponseUtils.error(ex);
            String jsonString = JSONUtils.toJSONString(responseDTO);

            LogUtils.e("== url ===>>>> {}", request.getRequestURI());
            LogUtils.e("== exception ===>>>> {}", jsonString);

            response.setContentType(ResponseUtils.JSON_CONTENT_TYPE);
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            LogUtils.e("{}", e);
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                LogUtils.e("{}", e);
            }
        }
        return null;
    }
}
