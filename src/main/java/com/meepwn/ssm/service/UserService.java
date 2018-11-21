package com.meepwn.ssm.service;

import com.meepwn.ssm.pojo.User;

import java.util.List;

public interface UserService {

    public void insertUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

    public User getUser(int id);

    public List<User> findUsers(String userName);

    public List<User> findAllUsers();


}
