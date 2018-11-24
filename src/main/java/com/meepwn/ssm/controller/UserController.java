package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.utils.ResponseUtils;
import com.meepwn.ssm.pojo.User;
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

//    @RequestMapping("/selectUser.do")
//    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int userId = Integer.parseInt(request.getParameter("id"));
//        User user = userService.getUser(userId);
//        ObjectMapper mapper = new ObjectMapper();
//        response.getWriter().write(mapper.writeValueAsString(user));
//        response.getWriter().close();
//    }

    @ResponseBody
    @RequestMapping(value = "/selectUser.do", method = RequestMethod.POST)
    public String selectUser(@RequestBody Map<String, String> params, HttpServletResponse response) throws IOException {
        String id = params.get("id");
        User user = userService.getUser(Integer.parseInt(id));
        return ResponseUtils.responseJSON(response, user, params);
    }

}
