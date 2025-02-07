package com.pam.sample.listener;

import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomProcessEventListener implements ProcessEventListener {

    @Override
    public void beforeProcessStarted(final ProcessStartedEvent event) {
        log.debug("ProcessName: '{}' prepare starting",
                event.getProcessInstance().getProcessName());
    }

    @Override
    public void afterProcessStarted(final ProcessStartedEvent event) {
        log.debug("ProcessName: '{}' started with ProcessInstanceId: '{}'",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId());
    }

    @Override
    public void beforeProcessCompleted(final ProcessCompletedEvent event) {
        log.debug("ProcessName: '{}' - ProcessInstanceId: '{}' prepare completion",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId());
    }

    @Override
    public void afterProcessCompleted(final ProcessCompletedEvent event) {
        log.debug("ProcessName: '{}' - ProcessInstanceId: '{}' completed with status: '{}'",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId(),
                event.getProcessInstance().getState());
    }

    @Override
    public void beforeNodeTriggered(final ProcessNodeTriggeredEvent event) {
        log.debug("ProcessName: '{}' - ProcessInstanceId: '{}' - NodeName: '{}' prepare trigger",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId(),
                event.getNodeInstance().getNodeName());
    }

    @Override
    public void afterNodeTriggered(final ProcessNodeTriggeredEvent event) {
        log.debug("ProcessName: '{}' - ProcessInstanceId: '{}' - NodeName: '{}' triggered",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId(),
                event.getNodeInstance().getNodeName());
    }

    @Override
    public void beforeNodeLeft(final ProcessNodeLeftEvent event) {
        log.debug("ProcessName: '{}' - ProcessInstanceId: '{}' - NodeName: '{}' prepare execution",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId(),
                event.getNodeInstance().getNodeName());
    }

    @Override
    public void afterNodeLeft(final ProcessNodeLeftEvent event) {
        log.debug("ProcessName: '{}' - ProcessInstanceId: '{}' - NodeName: '{}' executed",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId(),
                event.getNodeInstance().getNodeName());
    }

    @Override
    public void beforeVariableChanged(final ProcessVariableChangedEvent event) {
        log.debug(
                "ProcessName: '{}' - ProcessInstanceId: '{}' - Variable: '{}' current value: '{}'",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId(),
                event.getVariableId(), event.getOldValue());
    }

    @Override
    public void afterVariableChanged(final ProcessVariableChangedEvent event) {
        log.debug("ProcessName: '{}' - ProcessInstanceId: '{}' - Variabile: '{}' new value: '{}'",
                event.getProcessInstance().getProcessName(), event.getProcessInstance().getId(),
                event.getVariableId(), event.getNewValue());
    }
}
