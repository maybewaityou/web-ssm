package com.meepwn.ssm.controller;

import com.meepwn.ssm.enhance.annotation.method.POST;
import com.meepwn.ssm.entity.dto.UserRequestDTO;
import com.meepwn.ssm.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @POST("/selectUser.do")
    public Object selectUser(@RequestBody UserRequestDTO requestDTO) {
        int id = requestDTO.getId();
        return userService.getUser(id);
    }

    @POST("/findAllUsers.do")
    public Object findAllUsers() {
        return userService.findAllUsers();
    }

    @POST("/throwsEx.do")
    public Object throwsEx() {
        throw new RuntimeException("报错了...");
    }

}
