package com.meepwn.ssm.enhance.validator;

import com.meepwn.ssm.entity.dto.user.UserRegisterRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserUpdateRequestDTO;
import com.meepwn.ssm.entity.po.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author MeePwn
 */
public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterRequestDTO.class.equals(aClass) || UserUpdateRequestDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // 查询参数验证
        if (o instanceof UserRegisterRequestDTO) {
            UserRegisterRequestDTO target = (UserRegisterRequestDTO) o;
            User user = target.getUser();
            int id = user.getId();
            if (id < 0) {
                errors.rejectValue("id", "", "不能小于 0, 当前 id 为: " + id + " .");
            }
            // 更新参数验证
        } else if (o instanceof UserUpdateRequestDTO) {

        }
    }

}
