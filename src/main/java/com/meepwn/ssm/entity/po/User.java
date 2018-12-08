package com.meepwn.ssm.entity.po;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author MeePwn
 */
@Data
@Component
public class User {

    private int id;
    private String userName;
    private int age;

}
