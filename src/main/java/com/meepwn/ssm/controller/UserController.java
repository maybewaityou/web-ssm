package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.pojo.User;
import com.meepwn.ssm.pojo.response.ResponseModel;
import com.meepwn.ssm.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/selectUser.do", method = RequestMethod.POST)
    public ResponseModel selectUser(@RequestBody Map<String, String> params, HttpServletResponse response) throws IOException {
        String id = params.get("id");
        User user = userService.getUser(Integer.parseInt(id));
        return ResponseUtils.responseModel(response, user, params);
    }

    @RequestMapping(value = "/findAllUsers.do", method = RequestMethod.POST)
    public ResponseModel findAllUsers(HttpServletResponse response) throws IOException {
        List<User> users = userService.findAllUsers();
        return ResponseUtils.responseModel(response, users);
    }

    @RequestMapping(value = "/throwsEx.do", method = RequestMethod.POST)
    public ResponseModel throwsEx(HttpServletResponse response) throws IOException {
        throw new RuntimeException("报错了...");
    }

}
