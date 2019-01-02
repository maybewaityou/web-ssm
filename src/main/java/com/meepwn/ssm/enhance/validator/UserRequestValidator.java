package com.meepwn.ssm.enhance.validator;

import com.meepwn.ssm.entity.dto.user.UserPanRequestDTO;
import com.meepwn.ssm.entity.dto.user.UserRequestDTO;
import com.meepwn.ssm.entity.po.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            if (id < 0) {
                errors.rejectValue("id", "", "不能小于 0, 当前 id 为: " + id + " .");
            }
//            ValidatorFlow.of(user).validate(User::getId, tmpId -> tmpId < 0, "不能小于 0, 当前 id 为: " + id + " .");
        } else if (o instanceof UserPanRequestDTO) {
            UserPanRequestDTO target = (UserPanRequestDTO) o;
            Integer id = target.getId();
            if (id == null) {
                errors.rejectValue("id", "", "参数不存在.");
            } else if (id < 0) {
                errors.rejectValue("id", "", "不能小于 0, 当前 id 为: " + id + " .");
            }
        }
    }

}
