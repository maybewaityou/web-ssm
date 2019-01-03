package com.meepwn.ssm.entity.dto.exception;

import lombok.Data;

/**
 * @author MeePwn
 */
@Data
public class SemanticExceptionDetails {

    private String instruction;

    public SemanticExceptionDetails(String instruction) {
        this.instruction = instruction;
    }

}
