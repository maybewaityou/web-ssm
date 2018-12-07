package com.meepwn.ssm.entity.dto;

/**
 * @author MeePwn
 */
public class UserSelectDTO {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserSelectDTO{" +
                "id=" + id +
                '}';
    }
}
