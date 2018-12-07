package com.meepwn.ssm.entity.dto;

import com.meepwn.ssm.entity.po.User;

/**
 * @author MeePwn
 */
public class UserUpdateRequestDTO {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserUpdateRequestDTO{" +
                "user=" + user +
                '}';
    }
}
