package com.pam.sample.service;

import java.util.UUID;
import com.pam.sample.model.StartProcessDTO;
import com.pam.sample.model.StartProcessResponseDTO;
import com.pam.sample.model.WakeUpSignalDTO;
import com.pam.sample.util.Constants;
import org.jbpm.services.api.ProcessService;
import org.kie.internal.KieInternalServices;
import org.kie.internal.process.CorrelationKey;
import org.kie.internal.process.CorrelationKeyFactory;
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
    private final ProcessService processService;
    private final CorrelationKeyFactory correlationKeyFactory =
            KieInternalServices.Factory.get().newCorrelationKeyFactory();

    public StartProcessResponseDTO startProcess(final StartProcessDTO request) {
        Span span = this.tracer.spanBuilder("startProcess").startSpan();
        StartProcessResponseDTO result = new StartProcessResponseDTO();
        try (Scope scope = span.makeCurrent()) {
            CorrelationKey newCorrelationKey =
                    this.correlationKeyFactory.newCorrelationKey(UUID.randomUUID().toString());
            final Long processInstanceId = this.processService.startProcess(request.getKjar(),
                    request.getProcessName(), newCorrelationKey, request.getParameters());
            log.info("Process started with processInstanceId: {} and correlationKey: {}",
                    processInstanceId, newCorrelationKey.getName());
            result.setCorrelationKey(newCorrelationKey.getName());
            result.setProcessInstanceId(processInstanceId);
            span.addEvent("Process started");
            span.setAttribute(Constants.SPAN_KEY_PROCESS_NAME, request.getProcessName());
            span.setAttribute(Constants.SPAN_KEY_PROCESS_INSTANCE_ID, processInstanceId);
            span.setAttribute(Constants.SPAN_KEY_CORRELATION_KEY, newCorrelationKey.getName());
        } finally {
            span.end();
        }
        return result;
    }

    public void wakeUpSignal(final WakeUpSignalDTO request) {
        Span span = this.tracer.spanBuilder("wakeUpSignal").startSpan();
        try (Scope scope = span.makeCurrent()) {
            this.processService.signalProcessInstance(request.getProcessInstanceId(),
                    request.getSignalName(), request.getParameters());
            log.info("Signal activated !");
            span.addEvent("Signal activated");
            span.setAttribute("signalName", request.getSignalName());
            span.setAttribute(Constants.SPAN_KEY_PROCESS_INSTANCE_ID,
                    request.getProcessInstanceId());
            span.setAttribute(Constants.SPAN_KEY_CORRELATION_KEY, request.getCorrelationKey());
        } finally {
            span.end();
        }
    }
}
