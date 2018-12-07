package com.meepwn.ssm.enhance.validator;

import com.meepwn.ssm.entity.dto.UserRequestDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author MeePwn
 */
public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRequestDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRequestDTO target = (UserRequestDTO) o;
        Integer id = target.getId();
        if (id == null) {
            errors.rejectValue("id", "", "参数不存在.");
        } else if (id < 0) {
            errors.rejectValue("id", "", "不能小于 0.");
        }
    }

}
