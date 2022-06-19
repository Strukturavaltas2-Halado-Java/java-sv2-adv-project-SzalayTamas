package com.example.javasv2advprojectszalaytamas.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class IllegalInvoiceStatusException extends AbstractThrowableProblem {
    public IllegalInvoiceStatusException(String string) {
        super(URI.create("invoice/wrong-invoice-status"),
                "Illegal invoice status",
                Status.NOT_ACCEPTABLE,
                String.format("Invoice status is incorrect : %s", string));
    }
}
