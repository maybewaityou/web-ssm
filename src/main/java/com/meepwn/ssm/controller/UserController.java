package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.pojo.User;
import com.meepwn.ssm.pojo.response.ResponseModel;
import com.meepwn.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/selectUser.do", method = RequestMethod.POST)
    public ResponseModel selectUser(@RequestBody Map<String, String> params, HttpServletResponse response) throws IOException {
        String id = params.get("id");
        User user = userService.getUser(Integer.parseInt(id));
        return ResponseUtils.responseModel(response, user, params);
    }

    @ResponseBody
    @RequestMapping(value = "/findAllUsers.do", method = RequestMethod.POST)
    public ResponseModel findAllUsers(HttpServletResponse response) throws IOException {
        List<User> users = userService.findAllUsers();
        return ResponseUtils.responseModel(response, users);
    }

    @ResponseBody
    @RequestMapping(value = "/throwsEx.do", method = RequestMethod.POST)
    public ResponseModel throwsEx(HttpServletResponse response) throws IOException {
        throw new RuntimeException("报错了...");
    }

}
