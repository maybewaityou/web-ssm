package com.meepwn.ssm.enhance.validator;

import com.meepwn.ssm.enhance.exception.ParamsPreparedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author MeePwn
 */
public class Validators<T> {

    private final T t;
    private final List<Throwable> exceptions = new ArrayList<>();

    private Validators(T t) {
        this.t = t;
    }

    public static <T> Validators<T> of(T t) {
        return new Validators<>(Objects.requireNonNull(t));
    }

    public Validators<T> validate(Predicate<T> validation, String message) {
        if (!validation.test(t)) {
            exceptions.add(new ParamsPreparedException(message));
        }
        return this;
    }

    public <U> Validators<T> validate(Function<T, U> projection, Predicate<U> validation, String message) {
        return validate(projection.andThen(validation::test)::apply, message);
    }

    public T get() {
        if (exceptions.isEmpty()) {
            return t;
        }
        ParamsPreparedException e = new ParamsPreparedException("请求参数错误");
        exceptions.forEach(e::addSuppressed);
        throw e;
    }

    public boolean hasError() {
        return !exceptions.isEmpty();
    }

    public void apply() {
        if (hasError()) {
            ParamsPreparedException e = new ParamsPreparedException("请求参数错误");
            exceptions.forEach(e::addSuppressed);
            throw e;
        }
    }

}
