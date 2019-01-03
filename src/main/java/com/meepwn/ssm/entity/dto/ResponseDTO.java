package com.meepwn.ssm.entity.dto;

import lombok.Getter;

/**
 * @author MeePwn
 * <p>
 * 响应数据 ( 通用父类 )
 */
public class ResponseDTO {

    @Getter
    protected String retCode;

    @Getter
    protected String retMsg;

}
