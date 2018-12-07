package com.meepwn.ssm.controller;

import com.meepwn.ssm.enhance.annotation.method.POST;
import com.meepwn.ssm.enhance.exception.ParamsPreparedException;
import com.meepwn.ssm.entity.dto.UserRequestDTO;
import com.meepwn.ssm.entity.po.User;
import com.meepwn.ssm.service.UserService;
import org.springframework.validation.Errors;
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

    /// 验证器
//    @InitBinder
//    public void initBinder(DataBinder binder) {
//        binder.setValidator(new UserRequestValidator());
//    }

    @POST("/selectUser.do")
    public Object selectUser(@Valid @RequestBody UserRequestDTO requestDTO, Errors errors) {
        int id = requestDTO.getId();
        return userService.getUser(id);
    }

    @POST("/updateUser.do")
    public Object updateUser(@RequestBody User user) {
        userService.updateUser(user);
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
