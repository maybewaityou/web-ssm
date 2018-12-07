package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.util.RedisUtils;
import com.meepwn.ssm.enhance.annotation.method.POST;
import com.meepwn.ssm.enhance.exception.ParamsPreparedException;
import com.meepwn.ssm.enhance.validator.UserRequestValidator;
import com.meepwn.ssm.entity.dto.UserRequestDTO;
import com.meepwn.ssm.entity.po.User;
import com.meepwn.ssm.service.UserService;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author MeePwn
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RedisUtils redisUtils;

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserRequestValidator());
    }

    @POST("/selectUser.do")
    public Object selectUser(@Valid @RequestBody UserRequestDTO requestDTO, Errors errors) {
        int id = requestDTO.getId();
        String key = "user" + id;
        if (redisUtils.exists(key)) {
            return redisUtils.get(key);
        }
        User user = userService.getUser(id);
        System.out.println(user);
        redisUtils.set(key, user);
        return user;
    }

    @POST("/findAllUsers.do")
    public Object findAllUsers() {
        return userService.findAllUsers();
    }

    @POST("/throwsEx.do")
    public Object throwsEx() {
        throw new ParamsPreparedException("报错了...");
    }

}
