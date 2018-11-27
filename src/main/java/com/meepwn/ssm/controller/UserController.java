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
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/selectUser.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel selectUser(@RequestBody Map<String, String> params, HttpServletResponse response) throws IOException {
        String id = params.get("id");
        User user = userService.getUser(Integer.parseInt(id));
        return ResponseUtils.responseModel(response, user, params);
    }

}
