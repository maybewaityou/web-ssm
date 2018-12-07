package com.meepwn.ssm.mapper;

import com.meepwn.ssm.entity.po.User;

import java.util.List;

/**
 * @author MeePwn
 */
public interface UserMapper {

    /**
     * 插入用户
     *
     * @param user 用户
     */
    void insertUser(User user);

    /**
     * 根据 id 删除用户
     *
     * @param id 用户 id
     */
    void deleteUser(int id);

    /**
     * 更新用户
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 根据用户 id 获取用户
     *
     * @param id 用户 id
     * @return 用户
     */
    User getUser(int id);

    /**
     * 根据用户名关键字, 模糊匹配用户
     *
     * @param userName 用户名关键字
     * @return 用户列表
     */
    List<User> findUsers(String userName);

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    List<User> findAllUsers();

}
