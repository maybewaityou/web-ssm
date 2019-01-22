package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.common.util.FileUtils;
import com.meepwn.ssm.common.util.ResponseUtils;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import com.meepwn.ssm.entity.dto.file.FileDownloadRequestDTO;
import com.meepwn.ssm.entity.po.Empty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author MeePwn
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private Empty empty;

    @PostMapping("/fileUpload.do")
    @ResponseAdvice(success = ResponseEnum.FILE_UPLOAD_SUCCESS, failure = ResponseEnum.FILE_UPLOAD_FAILURE)
    public Object fileUpload(MultipartFile file) {
        return empty;
    }

    @GetMapping("/fileDownload.do")
    @ResponseAdvice(success = ResponseEnum.FILE_DOWNLOAD_SUCCESS, failure = ResponseEnum.FILE_DOWNLOAD_FAILURE)
    public ResponseEntity<byte[]> fileDownload(FileDownloadRequestDTO requestDTO) {
        String moduleName = requestDTO.getModuleName();
        String bundleType = requestDTO.getBundleType();
        File file = new File("/Users/MeePwn/Desktop/MeePwn/Sites/zzzz/" + moduleName + bundleType + "/bundle.zip");
        HttpHeaders headers = ResponseUtils.getDownloadHeaders(file);
        byte[] fileBytes = FileUtils.toByteArray(file);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

}
