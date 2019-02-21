package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import com.meepwn.ssm.entity.dto.hot_update.VersionCheckRequestDTO;
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
@RequestMapping("/version")
public class HotUpdateController {

    @PostMapping("/checkForUpdate.do")
    @ResponseAdvice(failure = ResponseEnum.FAILURE)
    public Object checkForUpdate(@RequestBody VersionCheckRequestDTO requestDTO) {
        String moduleName = requestDTO.getModuleName();
        Map<String, Integer> map = new HashMap<>(2);
        map.put("bundleVersion", 1);
        map.put("androidVersion", 10);
        return map;
    }

}
