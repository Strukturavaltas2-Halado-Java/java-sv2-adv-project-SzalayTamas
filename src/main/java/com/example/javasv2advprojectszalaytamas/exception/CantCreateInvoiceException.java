package com.example.javasv2advprojectszalaytamas.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CantCreateInvoiceException extends AbstractThrowableProblem {
    public CantCreateInvoiceException(Long id) {
        super(URI.create("invoice/invoice-not-found"),
                "Not Found",
                Status.NOT_ACCEPTABLE,
                String.format("Customer have no unbilled electricity usage, customer id: %d", id));
    }
}
