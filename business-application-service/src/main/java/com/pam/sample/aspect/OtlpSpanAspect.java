package com.pam.sample.aspect;

import com.pam.sample.util.Constants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@Aspect
@Component
public class OtlpSpanAspect {

    private final Tracer tracer;

    public OtlpSpanAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Pointcut(value = "@within(org.springframework.stereotype.Service)")
    public void createSpanPointCut() {
        // Pointcut
    }

    @Around("createSpanPointCut()")
    public Object createSpan(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed = null;
        String fullClassName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(Constants.C_DOT) + 1);
        String methodName = proceedingJoinPoint.getSignature().getName();
        Span span = this.tracer.spanBuilder(className + Constants.C_DOT + methodName).startSpan();
        try (Scope scope = span.makeCurrent()) {
            span.setAttribute(Constants.SPAN_KEY_CLASS_NAME, fullClassName);
            proceed = proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            span.recordException(ex);
            throw ex;
        } finally {
            span.end();
        }
        return proceed;
    }
}
