package com.meepwn.ssm.enhance.aop.trace;

import com.meepwn.ssm.common.util.JsonUtils;
import com.meepwn.ssm.common.util.LogUtils;
import com.meepwn.ssm.entity.dto.OutputDTO;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MeePwn
 */
@Component
public class TracePrinter {

    private static final ThreadLocal<Long> START_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal StartTime");
    private static final String URL_EL_STRING = "== url ===>>>> {}";
    private static final String PARAMS_EL_STRING = "== params ===>>>> {}";
    private static final String RESPONSE_EL_STRING = "== response ===>>>> {}";
    private static final String EXCEPTION_EL_STRING = "== exception ===>>>> {}";
    private static final String TIME_EL_STRING = "== network cost time ===>>>> {}";

    /**
     * 打印日志(请求)
     *
     * @param args    请求参数
     * @param request 请求
     */
    public void requestLog(Object[] args, HttpServletRequest request) {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            return;
        }

        Object params = args.length > 0 ? args[0] : new Object();
        long startTime = System.currentTimeMillis();
        // 线程绑定变量 (该数据只有当前请求的线程可见)
        START_TIME_THREAD_LOCAL.set(startTime);

        LogUtils.i(URL_EL_STRING, request.getRequestURI());
        if (params instanceof MultipartFile) {
            Map<String, String> paramsMap = new HashMap<>(10);
            MultipartFile file = (MultipartFile) params;
            paramsMap.put("name", file.getName());
            paramsMap.put("fileName", file.getOriginalFilename());
            paramsMap.put("fileSize", file.getSize() + "");
            LogUtils.i(PARAMS_EL_STRING, JsonUtils.toJSONString(paramsMap));
        } else {
            LogUtils.i(PARAMS_EL_STRING, JsonUtils.toJSONString(params));
        }
    }

    /**
     * 打印日志(响应)
     *
     * @param args      请求参数
     * @param outputDTO 响应报文
     * @param request   请求
     */
    public void responseLog(Object[] args, OutputDTO outputDTO, HttpServletRequest request) {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            return;
        }

        // 得到线程绑定的局部变量（开始时间）
        long beginTime = START_TIME_THREAD_LOCAL.get();
        // 结束时间
        long endTime = System.currentTimeMillis();

        LogUtils.i(URL_EL_STRING, request.getRequestURI());
        LogUtils.i(RESPONSE_EL_STRING, JsonUtils.toJSONString(outputDTO));
        LogUtils.i(TIME_EL_STRING, (endTime - beginTime));

        START_TIME_THREAD_LOCAL.remove();
    }

    /**
     * 打印日志(异常)
     *
     * @param args      请求参数
     * @param outputDTO 响应实体
     * @param request   请求
     */
    public void exceptionLog(Object[] args, OutputDTO outputDTO, HttpServletRequest request) {
        if (args.length > 0 && args[0] instanceof DataBinder) {
            return;
        }

        // 得到线程绑定的局部变量（开始时间）
        long beginTime = START_TIME_THREAD_LOCAL.get();
        // 结束时间
        long endTime = System.currentTimeMillis();

        LogUtils.e(URL_EL_STRING, request.getRequestURI());
        LogUtils.e(EXCEPTION_EL_STRING, JsonUtils.toJSONString(outputDTO));
        LogUtils.e(TIME_EL_STRING, (endTime - beginTime));

        START_TIME_THREAD_LOCAL.remove();
    }


}
