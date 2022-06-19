package com.example.javasv2advprojectszalaytamas.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class IllegalMeasurementException extends AbstractThrowableProblem {
    public IllegalMeasurementException() {
        super(URI.create("meter/wrong-measurement-data"),
                "Illegal measurement data",
                Status.NOT_ACCEPTABLE,
                String.format("Illegal measurement data"));
    }
}
