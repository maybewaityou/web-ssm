package com.meepwn.ssm.entity.dto.user;

/**
 * @author MeePwn
 */
public class UserSelectRequestDTO {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserSelectRequestDTO{" +
                "id=" + id +
                '}';
    }
}
