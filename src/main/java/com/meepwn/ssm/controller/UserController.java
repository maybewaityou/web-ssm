package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import com.meepwn.ssm.enhance.exception.ParamsPreparedException;
import com.meepwn.ssm.enhance.validator.UserRequestValidator;
import com.meepwn.ssm.entity.dto.ResponseDTO;
import com.meepwn.ssm.entity.dto.user.UserRegisterRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserSelectRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserUpdateRequestDTO;
import com.meepwn.ssm.entity.po.User;
import com.meepwn.ssm.service.UserService;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author MeePwn
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserRequestValidator());
    }

    @PostMapping("/registerUser.do")
    @ResponseAdvice(failure = ResponseEnum.FAILURE)
    public Object registerUser(@Valid @RequestBody UserRegisterRequestDTO requestDTO, Errors errors) {
        User user = requestDTO.getUser();
        userService.insertUser(user);
        return userService.findUsers(user.getUserName());
    }

    @PostMapping("/updateUser.do")
    @ResponseAdvice(failure = ResponseEnum.USER_UPDATE_FAILURE)
    public Object updateUser(@RequestBody UserUpdateRequestDTO requestDTO) {
        User user = requestDTO.getUser();
        userService.updateUser(user);
        return user;
    }

    @PostMapping("/selectUser.do")
    @ResponseAdvice(failure = ResponseEnum.USER_NOT_EXIST)
    public Object selectUser(@Valid @RequestBody UserSelectRequestDTO requestDTO, Errors errors) {
        int id = requestDTO.getId();
        return userService.getUser(id);
    }

    @PostMapping("/findAllUsers.do")
    @ResponseAdvice(failure = ResponseEnum.USER_LIST_IS_EMPTY)
    public Object findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/throwsEx.do")
    public ResponseDTO throwsEx() {
        throw new ParamsPreparedException("报错了...");
    }

}
