package com.meepwn.ssm.entity.dto;

import com.meepwn.ssm.entity.po.User;

public class UserUpdateDTO {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
                "user=" + user +
                '}';
    }
}
