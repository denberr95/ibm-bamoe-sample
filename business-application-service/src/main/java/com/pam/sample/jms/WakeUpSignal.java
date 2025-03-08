package com.pam.sample.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pam.sample.model.WakeUpSignalDTO;
import org.jbpm.services.api.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class WakeUpSignal {

    private static final Logger log = LoggerFactory.getLogger(WakeUpSignal.class);
    private final ProcessService processService;
    private final ObjectMapper objectMapper;


    public WakeUpSignal(ProcessService processService, ObjectMapper objectMapper) {
        this.processService = processService;
        this.objectMapper = objectMapper;
    }


    @JmsListener(destination = "wake-up-signal")
    public void wakeUp(String message) throws JsonProcessingException {
        WakeUpSignalDTO wakeUpSignal = this.objectMapper.readValue(message, WakeUpSignalDTO.class);
        log.info("JMS Wake Up signal: '{}'", wakeUpSignal.getSignalName());
        this.processService.signalProcessInstance(wakeUpSignal.getProcessInstanceId(),
                wakeUpSignal.getSignalName(), wakeUpSignal.getParameters());
    }
}
