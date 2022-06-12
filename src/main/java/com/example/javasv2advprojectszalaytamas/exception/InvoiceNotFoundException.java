package com.example.javasv2advprojectszalaytamas.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvoiceNotFoundException extends AbstractThrowableProblem {
    public InvoiceNotFoundException(Long id) {
        super(URI.create("invoice/invoice-not-found"),
                "Not Found",
                Status.NOT_FOUND,
                String.format("Invoice not found, id: %d", id));
    }
}
