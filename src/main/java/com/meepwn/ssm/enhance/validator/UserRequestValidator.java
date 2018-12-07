package com.meepwn.ssm.enhance.validator;

import com.meepwn.ssm.entity.dto.UserSelectDTO;
import com.meepwn.ssm.entity.dto.UserUpdateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author MeePwn
 */
public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserSelectDTO.class.equals(aClass) || UserUpdateDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // 查询参数验证
        if (o instanceof UserSelectDTO) {
            UserSelectDTO target = (UserSelectDTO) o;
            Integer id = target.getId();
            if (id == null) {
                errors.rejectValue("id", "", "参数不存在.");
            } else if (id < 0) {
                errors.rejectValue("id", "", "不能小于 0.");
            }
            // 更新参数验证
        } else if (o instanceof UserUpdateDTO) {

        }
    }

}
