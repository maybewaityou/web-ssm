package com.meepwn.ssm.manager;

import com.meepwn.ssm.common.manager.RedisManager;
import com.meepwn.ssm.dao.UserDao;
import com.meepwn.ssm.entity.po.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MeePwn
 */
@Component
public class UserManager {

    @Resource
    private UserDao userDao;
    @Resource
    private RedisManager redisUtils;

    private static final String KEY_PREFIX = "user_";

    public void insertUser(User user) {
        userDao.insertUser(user);
        String key = KEY_PREFIX + user.getId();
        if (!redisUtils.exists(key)) {
            redisUtils.set(key, user);
        }
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
        String key = KEY_PREFIX + id;
        if (redisUtils.exists(key)) {
            redisUtils.remove(key);
        }
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
        String key = KEY_PREFIX + user.getId();
        if (redisUtils.exists(key)) {
            redisUtils.set(key, user);
        }
    }

    public User getUser(int id) {
        String key = KEY_PREFIX + id;
        if (redisUtils.exists(key)) {
            return (User) redisUtils.get(key);
        }
        User user = userDao.getUser(id);
        redisUtils.set(key, user);
        return user;
    }

    public List<User> findUsers(String userName) {
        return userDao.findUsers(userName);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

}
