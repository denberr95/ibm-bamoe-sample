package com.pam.sample.service;

import org.springframework.stereotype.Service;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {

    private final Tracer tracer;

    public void myMethod() {
        Span span = this.tracer
                .spanBuilder(StackWalker.getInstance()
                        .walk(frames -> frames.skip(0).findFirst().get().getMethodName()))
                .startSpan();
        try (Scope scope = span.makeCurrent()) {
            log.info("Business Service");
            Span.current().addEvent("Finished working");
        } finally {
            span.end();
        }
    }
}
