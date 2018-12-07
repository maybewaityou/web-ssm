package com.meepwn.ssm.service.impl;

import com.meepwn.ssm.common.util.RedisUtils;
import com.meepwn.ssm.dao.UserDao;
import com.meepwn.ssm.entity.po.User;
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
    private UserDao userDao;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUser(int id) {
        String key = "user_" + id;
        if (redisUtils.exists(key)) {
            return (User) redisUtils.get(key);
        }
        User user = userDao.getUser(id);
        redisUtils.set(key, user);
        return user;
    }

    @Override
    public List<User> findUsers(String userName) {
        return userDao.findUsers(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

}
