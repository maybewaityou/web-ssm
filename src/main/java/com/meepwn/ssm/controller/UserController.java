package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import com.meepwn.ssm.enhance.annotation.method.POST;
import com.meepwn.ssm.enhance.exception.ParamsPreparedException;
import com.meepwn.ssm.enhance.validator.UserRequestValidator;
import com.meepwn.ssm.entity.dto.ResponseDTO;
import com.meepwn.ssm.entity.dto.user.UserSelectRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserUpdateRequestDTO;
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

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserRequestValidator());
    }

    @POST("/selectUser.do")
    @ResponseAdvice(failure = ResponseEnum.USER_NOT_EXIST)
    public Object selectUser(@Valid @RequestBody UserSelectRequestDTO requestDTO, Errors errors) {
        int id = requestDTO.getId();
        return userService.getUser(id);
    }

    @POST("/updateUser.do")
    @ResponseAdvice(failure = ResponseEnum.USER_UPDATE_FAILURE)
    public Object updateUser(@RequestBody UserUpdateRequestDTO requestDTO) {
        User user = requestDTO.getUser();
        userService.updateUser(user);
        return user;
    }

    @POST("/findAllUsers.do")
    @ResponseAdvice(failure = ResponseEnum.USER_LIST_IS_EMPTY)
    public Object findAllUsers() {
        return userService.findAllUsers();
    }

    @POST("/throwsEx.do")
    public ResponseDTO throwsEx() {
        throw new ParamsPreparedException("报错了...");
    }

}
