package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MeePwn
 */
@RestController
@RequestMapping("/version")
public class HotUpdateController {

    @PostMapping("/checkForUpdate.do")
    @ResponseAdvice(failure = ResponseEnum.FAILURE)
    public Object registerUser() {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("bundleVersion", 2);
        map.put("androidVersion", 10);
        return map;
    }

}