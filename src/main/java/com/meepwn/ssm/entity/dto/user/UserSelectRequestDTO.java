package com.meepwn.ssm.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meepwn.ssm.common.constant.Constant;
import lombok.Data;

import java.util.Date;

/**
 * @author MeePwn
 */
@Data
public class UserSelectRequestDTO {

    private Integer id;
    @JsonFormat(pattern = Constant.DATE_FORMAT)
    private Date date;
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private Date dateTime;

}
