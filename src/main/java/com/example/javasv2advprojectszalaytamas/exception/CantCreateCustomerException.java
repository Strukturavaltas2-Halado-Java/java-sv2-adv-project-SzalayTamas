package com.example.javasv2advprojectszalaytamas.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CantCreateCustomerException extends AbstractThrowableProblem {
    public CantCreateCustomerException(String email) {
        super(URI.create("customer/email-is-taken"),
                "Taken Email Address",
                Status.NOT_ACCEPTABLE,
                String.format("The given email address is already in the system %s", email));
    }
}
