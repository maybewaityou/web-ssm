package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.enhance.annotation.advice.ResponseAdvice;
import com.meepwn.ssm.enhance.exception.ParamsPreparedException;
import com.meepwn.ssm.enhance.validator.UserRequestValidator;
import com.meepwn.ssm.entity.dto.user.UserPanRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserRequestDTO;
import com.meepwn.ssm.entity.po.Empty;
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
    @Resource
    private Empty empty;

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserRequestValidator());
    }

    @PostMapping("/registerUser.do")
    @ResponseAdvice(failure = ResponseEnum.FAILURE)
    public Object registerUser(@Valid @RequestBody UserRequestDTO requestDTO, Errors errors) {
        User user = requestDTO.getUser();
        userService.insertUser(user);
        return empty;
    }

    @PostMapping("/updateUser.do")
    @ResponseAdvice(failure = ResponseEnum.USER_UPDATE_FAILURE)
    public Object updateUser(@Valid @RequestBody UserRequestDTO requestDTO, Errors errors) {
        User user = requestDTO.getUser();
        userService.updateUser(user);
        return user;
    }

    @PostMapping("/selectUser.do")
    @ResponseAdvice(failure = ResponseEnum.USER_NOT_EXIST)
    public Object selectUser(@Valid @RequestBody UserPanRequestDTO requestDTO, Errors errors) {
        int id = requestDTO.getId();
        return userService.getUser(id);
    }

    @PostMapping("/deleteUser.do")
    public Object deleteUser(@Valid @RequestBody UserPanRequestDTO requestDTO, Errors errors) {
        int id = requestDTO.getId();
        userService.deleteUser(id);
        return empty;
    }

    @PostMapping("/findAllUsers.do")
    @ResponseAdvice(failure = ResponseEnum.USER_LIST_IS_EMPTY)
    public Object findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/throwsEx.do")
    public Object throwsEx() {
        throw new ParamsPreparedException("报错了...");
    }

    @PostMapping("/test.do")
    @ResponseAdvice(failure = ResponseEnum.USER_NOT_EXIST)
    public Object test(Errors errors) {
        return empty;
    }
}
