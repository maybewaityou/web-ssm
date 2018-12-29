package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MeePwn
 */
@RestController
@RequestMapping("/mock")
public class MockController {

    @PostMapping("/mockResponse.do")
    @ResponseAdvice(failure = ResponseEnum.FAILURE)
    public Object mockResponse() {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("bundleVersion", 2);
        map.put("androidVersion", 10);
        return map;
    }

    @PostMapping("/mockMirror.do")
    @ResponseAdvice(failure = ResponseEnum.FAILURE)
    public Object mockResponseWithParams(@RequestBody Map paramsMap) {
        return paramsMap;
    }

}
