package com.meepwn.ssm.common.util;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.factory.bean.BeanFactory;
import com.meepwn.ssm.entity.dto.DataDTO;
import com.meepwn.ssm.entity.dto.ExceptionOutputDTO;
import com.meepwn.ssm.entity.dto.OutputDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletResponse;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author MeePwn
 */
public class ResponseUtils {

    public static final String JSON_CONTENT_TYPE = "application/json";

    /**
     * 获取下载文件 Headers
     * @param file 文件
     * @return 响应头
     */
    public static HttpHeaders getDownloadHeaders(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        headers.setContentLength(file.length());
        return headers;
    }

    /**
     * 组装返回数据
     *
     * @param value       返回数据
     * @param failureEnum 失败枚举
     * @return 响应数据
     */
    public static OutputDTO outputDTO(Object value, ResponseEnum failureEnum) {
        return outputDTO(value, ResponseEnum.SUCCESS, failureEnum);
    }

    /**
     * 组装返回数据
     *
     * @param value       返回数据
     * @param successEnum 成功枚举
     * @param failureEnum 失败枚举
     * @return 响应数据
     */
    public static OutputDTO outputDTO(Object value, ResponseEnum successEnum, ResponseEnum failureEnum) {
        DataDTO.Builder builder = (DataDTO.Builder) BeanFactory.newInstance(DataDTO.Builder.class);
        DataDTO dataDTO = Objects.requireNonNull(builder)
                .setSuccessEnum(successEnum)
                .setFailureEnum(failureEnum)
                .setValue(value)
                .build();
        return outputDTO(dataDTO);
    }

    /**
     * 响应数据
     *
     * @param dataDTO 返回结果
     * @param args        可选参数
     * @return 响应数据
     */
    public static OutputDTO outputDTO(DataDTO dataDTO, Object... args) {
        Object value = dataDTO.getValue();
        if (value instanceof List) {
            return responseListModel(null, dataDTO, args);
        } else {
            return responseModel(null, dataDTO, args);
        }
    }

    public static ExceptionOutputDTO error(Throwable exception) {
        ExceptionOutputDTO.Builder builder = (ExceptionOutputDTO.Builder) BeanFactory.newInstance(ExceptionOutputDTO.Builder.class);
        return Objects.requireNonNull(builder)
                .setRetCode(ResponseEnum.EXCEPTION.getRetCode())
                .setRetMsg(ResponseEnum.EXCEPTION.getRetMsg() + ": " + exception.getMessage())
                .setException(exception)
                .build();
    }

    /**
     * 响应数据
     *
     * @param response    响应对象
     * @param dataDTO 返回数据
     * @param args        可选参数
     * @return 响应数据
     */
    public static OutputDTO responseModel(ServletResponse response, DataDTO dataDTO, Object... args) {
        Object value = dataDTO.getValue();
        OutputDTO.Builder builder = setResponseInfo(value == null, dataDTO.getSuccessEnum(), dataDTO.getFailureEnum());
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
     * @param dataDTO 返回数据
     * @param args        可选参数
     * @return 响应数据
     */
    public static OutputDTO responseListModel(ServletResponse response, DataDTO dataDTO, Object... args) {
        List list = (List) dataDTO.getValue();
        boolean isEmpty = list.isEmpty();
        OutputDTO.Builder builder = setResponseInfo(isEmpty, dataDTO.getSuccessEnum(), dataDTO.getFailureEnum());
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
