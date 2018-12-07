package com.meepwn.ssm.service.impl;

import com.meepwn.ssm.entity.po.User;
import com.meepwn.ssm.manager.UserManager;
import com.meepwn.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MeePwn
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserManager userManager;

    @Override
    public void insertUser(User user) {
        userManager.insertUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userManager.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userManager.updateUser(user);
    }

    @Override
    public User getUser(int id) {
        return userManager.getUser(id);
    }

    @Override
    public List<User> findUsers(String userName) {
        return userManager.findUsers(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userManager.findAllUsers();
    }

}
