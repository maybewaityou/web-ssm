package com.meepwn.ssm.enhance.annotation.advice;

import com.meepwn.ssm.common.constant.response.ResponseEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseAdvice {

    ResponseEnum success() default ResponseEnum.SUCCESS;
    ResponseEnum failure() default ResponseEnum.FAILURE;

}
