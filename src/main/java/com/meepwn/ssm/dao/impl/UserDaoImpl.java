package com.meepwn.ssm.dao.impl;

import com.meepwn.ssm.dao.BaseDao;
import com.meepwn.ssm.dao.UserDao;
import com.meepwn.ssm.mapper.UserMapper;
import com.meepwn.ssm.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    private UserMapper userMapper;

    @Override
    public void init() {
        userMapper = getSqlSession().getMapper(UserMapper.class);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> findUsers(String userName) {
        return userMapper.findUsers(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

}
