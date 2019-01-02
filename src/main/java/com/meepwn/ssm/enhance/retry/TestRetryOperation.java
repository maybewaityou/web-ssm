package com.meepwn.ssm.enhance.retry;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author MeePwn
 */
public class TestRetryOperation implements BusinessOperation<String> {

    private final Deque<BusinessException> errors;
    private final BusinessException exception;

    public TestRetryOperation(BusinessException... errors) {
        this.errors = new ArrayDeque<>(Arrays.asList(errors));
        exception = errors[0];
    }

    @Override
    public String perform() throws BusinessException {
        if (!this.errors.isEmpty()) {
            throw this.errors.pop();
        }
        if (exception != null) {
            throw exception;
        }
        return "mu ha ha ~ ~";
    }

}
