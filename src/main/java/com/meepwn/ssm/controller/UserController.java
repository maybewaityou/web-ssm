package com.meepwn.ssm.controller;

import com.meepwn.ssm.common.constant.response.ResponseEnum;
import com.meepwn.ssm.common.util.ResponseUtils;
import com.meepwn.ssm.enhance.annotation.method.POST;
import com.meepwn.ssm.enhance.exception.ParamsPreparedException;
import com.meepwn.ssm.enhance.validator.UserRequestValidator;
import com.meepwn.ssm.entity.dto.OutputDTO;
import com.meepwn.ssm.entity.dto.ResponseDTO;
import com.meepwn.ssm.entity.dto.user.UserSelectRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserUpdateRequestDTO;
import com.meepwn.ssm.entity.po.User;
import com.meepwn.ssm.service.UserService;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author MeePwn
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserRequestValidator());
    }

    @POST("/selectUser.do")
    public OutputDTO selectUser(@Valid @RequestBody UserSelectRequestDTO requestDTO, Errors errors) {
        int id = requestDTO.getId();
        User user = userService.getUser(id);
        return ResponseUtils.outputDTO(user, ResponseEnum.USER_NOT_EXIST);
    }

    @POST("/updateUser.do")
    public OutputDTO updateUser(@RequestBody UserUpdateRequestDTO requestDTO) {
        User user = requestDTO.getUser();
        userService.updateUser(user);
        return ResponseUtils.outputDTO(user, ResponseEnum.USER_UPDATE_FAILURE);
    }

    @POST("/findAllUsers.do")
    public OutputDTO findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseUtils.outputDTO(users, ResponseEnum.QUERY_USERS_FAILURE);
    }

    @POST("/throwsEx.do")
    public ResponseDTO throwsEx() {
        throw new ParamsPreparedException("报错了...");
    }

}
