package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.enhance.annotation.method.POST;
import com.meepwn.ssm.entity.dto.ResponseModel;
import com.meepwn.ssm.entity.dto.UserRequestDTO;
import com.meepwn.ssm.entity.pojo.User;
import com.meepwn.ssm.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @POST("/selectUser.do")
    public ResponseModel selectUser(@RequestBody UserRequestDTO requestDTO, HttpServletResponse response) {
        int id = requestDTO.getId();
        User user = userService.getUser(id);
        return ResponseUtils.responseModel(response, user, requestDTO);
    }

    @POST("/findAllUsers.do")
    public ResponseModel findAllUsers(HttpServletResponse response) {
        List<User> users = userService.findAllUsers();
        return ResponseUtils.responseModel(response, users);
    }

    @POST("/throwsEx.do")
    public ResponseModel throwsEx(HttpServletResponse response) {
        throw new RuntimeException("报错了...");
    }

}
