package com.pam.sample.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.pam.sample.model.StartProcessDTO;
import com.pam.sample.model.StartProcessResponseDTO;
import com.pam.sample.model.WakeUpSignalDTO;
import com.pam.sample.util.Constants;
import org.jbpm.services.api.ProcessService;
import org.kie.internal.KieInternalServices;
import org.kie.internal.process.CorrelationKey;
import org.kie.internal.process.CorrelationKeyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Service
public class BusinessService {

    private final ProcessService processService;
    private final CorrelationKeyFactory correlationKeyFactory =
            KieInternalServices.Factory.get().newCorrelationKeyFactory();

    private static final Logger log = LoggerFactory.getLogger(BusinessService.class);

    public BusinessService(ProcessService processService) {
        this.processService = processService;
    }

    public StartProcessResponseDTO startProcess(final StartProcessDTO request) {
        Span span = Span.current();
        StartProcessResponseDTO result = new StartProcessResponseDTO();
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
        return result;
    }

    public void wakeUpSignal(final WakeUpSignalDTO request) {
        Span span = Span.current();
        this.processService.signalProcessInstance(request.getProcessInstanceId(),
                request.getSignalName(), request.getParameters());
        log.info("Signal activated with processInstanceId: '{}' !", request.getProcessInstanceId());
        span.addEvent("Signal activated");
        span.setAttribute(Constants.SPAN_KEY_SIGNAL_NAME, request.getSignalName());
        span.setAttribute(Constants.SPAN_KEY_PROCESS_INSTANCE_ID, request.getProcessInstanceId());
        span.setAttribute(Constants.SPAN_KEY_CORRELATION_KEY, request.getCorrelationKey());
    }

    @WithSpan
    public Map<String, String> environments() {
        Map<String, String> result = new HashMap<>();
        System.getenv().forEach(result::put);
        return result;
    }
}
