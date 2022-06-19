package com.example.javasv2advprojectszalaytamas.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class MeterNotFoundException extends AbstractThrowableProblem {
    public MeterNotFoundException(Long id) {
        super(URI.create("meter/meter-not-found"),
                "Not Found",
                Status.NOT_FOUND,
                String.format("meter not found, id: %d", id));
    }
}
