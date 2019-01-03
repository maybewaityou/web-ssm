package com.meepwn.ssm.enhance.validator;

import com.meepwn.ssm.entity.dto.user.UserPanRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserRequestDTO;
import com.meepwn.ssm.entity.po.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author MeePwn
 */
public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequestDTO.class.equals(clazz) || UserPanRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // 查询参数验证
        if (o instanceof UserRequestDTO) {
            UserRequestDTO target = (UserRequestDTO) o;
            User user = target.getUser();
            int id = user.getId();
            Validators.of(user)
                    .validate(User::getId, value -> value >= 0, "id不能小于0, 当前id为: " + id)
                    .validate(User::getUserName, Objects::nonNull, "userName不能为空")
                    .apply();
        } else if (o instanceof UserPanRequestDTO) {
            UserPanRequestDTO target = (UserPanRequestDTO) o;
            Integer id = target.getId();
            Validators.of(target)
                    .validate(UserPanRequestDTO::getId, Objects::nonNull, "id参数不存在")
                    .validate(UserPanRequestDTO::getId, value -> value >= 0, "id不能小于0, 当前id为: " + id)
                    .apply();
        }
    }

}
