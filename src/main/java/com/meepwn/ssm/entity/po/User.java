package com.meepwn.ssm.entity.po;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author MeePwn
 */
@Data
@Component
public class User {

    /**
     * 用户 ID
     */
    private int id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 签到时间
     */
    private Date clockInTime;

    /**
     * 签退时间
     */
    private Date clockOutTime;

    /**
     * 报工时间
     */
    private Date deltaTime;

}
