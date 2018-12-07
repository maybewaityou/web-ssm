package com.meepwn.ssm.enhance.factory.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author MeePwn
 */
public class JsonMapperFactory {

    public static ObjectMapper newInstance() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static ObjectMapper newInstance(SerializationFeature serializationFeature) {
        return new ObjectMapper().disable(serializationFeature);
    }

    private JsonMapperFactory() {
    }

}
