package com.meepwn.ssm.entity.dto;

public class UserRequestDTO {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "id=" + id +
                '}';
    }
}
