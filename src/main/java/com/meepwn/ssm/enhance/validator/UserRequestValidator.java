package com.meepwn.ssm.enhance.validator;

import com.meepwn.ssm.entity.dto.UserRequestDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRequestDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRequestDTO target = (UserRequestDTO) o;
        int id = target.getId();
        if (id < 0) {
            errors.rejectValue("id", "","用户 id 不能小于 0.");
        }
    }

}
