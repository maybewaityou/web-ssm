package com.meepwn.ssm.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepwn.ssm.enhance.factory.json.JsonMapperFactory;

/**
 * @author MeePwn
 */
public class JsonUtils {

    /**
     * 将实体转为 JSON 字符串
     *
     * @param object 实体
     * @return JSON 字符串
     */
    public static String toJSONString(Object object) {
        ObjectMapper mapper = JsonMapperFactory.newInstance();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LogUtils.e("{}", e);
        }
        return "";
    }

    private JsonUtils() {
    }

}
