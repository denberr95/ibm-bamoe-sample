package com.pam.sample.controller;

import java.util.Collection;
import java.util.UUID;
import com.pam.sample.model.StartProcessDTO;
import com.pam.sample.model.StartProcessResponseDTO;
import com.pam.sample.model.WakeUpSignalDTO;
import com.pam.sample.service.BusinessService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.NodeInstanceDesc;
import org.kie.api.runtime.query.QueryContext;
import org.kie.internal.KieInternalServices;
import org.kie.internal.process.CorrelationKey;
import org.kie.internal.process.CorrelationKeyFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;
    private final ProcessService processService;
    private final RuntimeDataService runtimeDataService;
    private final CorrelationKeyFactory correlationKeyFactory =
            KieInternalServices.Factory.get().newCorrelationKeyFactory();

    @PostMapping(path = "start-process")
    public ResponseEntity<StartProcessResponseDTO> startProcess(
            @RequestBody final StartProcessDTO request) {
        log.info("Start process");
        this.businessService.myMethod();
        StartProcessResponseDTO result = new StartProcessResponseDTO();
        CorrelationKey newCorrelationKey =
                this.correlationKeyFactory.newCorrelationKey(UUID.randomUUID().toString());
        final Long processInstanceId = this.processService.startProcess(request.getKjar(),
                request.getProcessName(), newCorrelationKey, request.getParameters());
        log.info("Process started with processInstanceId: {} and correlationKey: {}",
                processInstanceId, newCorrelationKey.getName());
        result.setCorrelationKey(newCorrelationKey.getName());
        result.setProcessInstanceId(processInstanceId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(path = "wake-up-signal")
    public ResponseEntity<String> wakeUpSignal(@RequestBody final WakeUpSignalDTO request) {
        this.processService.signalProcessInstance(request.getProcessInstanceId(),
                request.getSignalName(), request.getParameters());
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @GetMapping(path = "check-nodes")
    public ResponseEntity<String> checkSignal(@RequestParam final Long processInstanceId,
            @RequestParam final String correlationKey) {
        log.info("Start check nodes");
        Collection<NodeInstanceDesc> activeInstances = this.runtimeDataService
                .getProcessInstanceHistoryActive(processInstanceId, new QueryContext());

        activeInstances.forEach(x -> {
            log.info("ACTIVE NODE INSTANCES - ProcessInstanceId: {}", x.getProcessInstanceId());
            log.info("ACTIVE NODE INSTANCES - NodeName: {}", x.getName());
            log.info("ACTIVE NODE INSTANCES - NodeId: {}", x.getNodeId());
            log.info("ACTIVE NODE INSTANCES - NodeType: {}", x.getNodeType());
            log.info("ACTIVE NODE INSTANCES - IsCompleted: {}", x.isCompleted());
        });

        Collection<NodeInstanceDesc> fullInstances = this.runtimeDataService
                .getProcessInstanceFullHistory(processInstanceId, new QueryContext());
        fullInstances.forEach(x -> {
            log.info("FULL NODE INSTANCES - ProcessInstanceId: {}", x.getProcessInstanceId());
            log.info("FULL NODE INSTANCES - NodeName: {}", x.getName());
            log.info("FULL NODE INSTANCES - NodeId: {}", x.getNodeId());
            log.info("FULL NODE INSTANCES - NodeType: {}", x.getNodeType());
            log.info("FULL NODE INSTANCES - IsCompleted: {}", x.isCompleted());
        });

        Collection<NodeInstanceDesc> completedInstances = this.runtimeDataService
                .getProcessInstanceHistoryCompleted(processInstanceId, new QueryContext());
        completedInstances.forEach(x -> {
            log.info("COMPLETED NODE INSTANCES - ProcessInstanceId: {}", x.getProcessInstanceId());
            log.info("COMPLETED NODE INSTANCES - NodeName: {}", x.getName());
            log.info("COMPLETED NODE INSTANCES - NodeId: {}", x.getNodeId());
            log.info("COMPLETED NODE INSTANCES - NodeType: {}", x.getNodeType());
            log.info("COMPLETED NODE INSTANCES - IsCompleted: {}", x.isCompleted());
        });

        log.info("End check nodes");
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
