package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.enhance.annotation.method.POST;
import com.meepwn.ssm.pojo.User;
import com.meepwn.ssm.pojo.response.ResponseModel;
import com.meepwn.ssm.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @POST("/selectUser.do")
    public ResponseModel selectUser(@RequestBody Map<String, String> params, HttpServletResponse response) throws IOException {
        String id = params.get("id");
        User user = userService.getUser(Integer.parseInt(id));
        return ResponseUtils.responseModel(response, user, params);
    }

    @POST("/findAllUsers.do")
    public ResponseModel findAllUsers(HttpServletResponse response) throws IOException {
        List<User> users = userService.findAllUsers();
        return ResponseUtils.responseModel(response, users);
    }

    @POST("/throwsEx.do")
    public ResponseModel throwsEx(HttpServletResponse response) throws IOException {
        throw new RuntimeException("报错了...");
    }

}
