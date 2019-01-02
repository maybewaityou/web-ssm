package com.meepwn.ssm.enhance.retry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author MeePwn
 */
public class Retry<T> implements BusinessOperation<T> {

    private final BusinessOperation<T> op;
    private final int maxAttempts;
    private final AtomicInteger attempts;
    private final List<Exception> errors;

    private final RemedyHandler remedyHandler;

    public Retry(BusinessOperation<T> op, int maxAttempts, RemedyHandler remedyHandler) {
        this.op = op;
        this.maxAttempts = maxAttempts;
        this.remedyHandler = remedyHandler;
        this.attempts = new AtomicInteger();
        this.errors = new ArrayList<>();
    }

    public List<Exception> errors() {
        return Collections.unmodifiableList(this.errors);
    }

    public int attempts() {
        return this.attempts.intValue();
    }

    @Override
    public T perform() throws BusinessException {
        do {
            try {
                return this.op.perform();
            } catch (BusinessException e) {
                this.errors.add(e);

                if (this.attempts.incrementAndGet() > this.maxAttempts) {
                    throw e;
                }
                try {
                    this.remedyHandler.remedy();
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        while (true);
    }

}
