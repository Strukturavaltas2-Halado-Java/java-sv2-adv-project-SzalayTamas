package com.example.javasv2advprojectszalaytamas.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CustomerNotFoundException extends AbstractThrowableProblem {
    public CustomerNotFoundException(Long id) {
        super(URI.create("customer/customer-not-found"),
                "Not Found",
                Status.NOT_FOUND,
                String.format("Customer not found, id: %d", id));
    }
}
