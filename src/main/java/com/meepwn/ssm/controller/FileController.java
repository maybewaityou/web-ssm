package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import com.meepwn.ssm.entity.po.Empty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author MeePwn
 */
@RequestMapping("/file")
@RestController
public class FileController {

    @Resource
    private Empty empty;

    @PostMapping("/fileUpload.do")
    @ResponseAdvice(success = ResponseEnum.FILE_UPLOAD_SUCCESS, failure = ResponseEnum.FILE_UPLOAD_FAILURE)
    public Object profileUpload(MultipartFile file) {
        return empty;
    }

}
