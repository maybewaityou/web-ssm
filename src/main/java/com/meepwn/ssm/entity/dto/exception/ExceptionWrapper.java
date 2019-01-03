package com.meepwn.ssm.entity.dto.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MeePwn
 */
@Data
public class ExceptionWrapper {

    private String message;
    private List<SemanticExceptionDetails> details;

    public ExceptionWrapper(Throwable throwable) {
        this.message = throwable.getMessage();
        Throwable[] throwables = throwable.getSuppressed();
        details = new ArrayList<>(throwables.length);
        for (Throwable t : throwables) {
            details.add(new SemanticExceptionDetails(t.getMessage()));
        }
    }

}
