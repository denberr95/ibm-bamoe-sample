package com.pam.sample.controller;

import javax.validation.Valid;
import com.pam.sample.model.StartProcessDTO;
import com.pam.sample.model.StartProcessResponseDTO;
import com.pam.sample.model.WakeUpSignalDTO;
import com.pam.sample.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Validated
@RestController
public class BusinessController {

    private final BusinessService businessService;
    private static final Logger log = LoggerFactory.getLogger(BusinessController.class);

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @WithSpan
    @PostMapping(path = "start-process")
    public ResponseEntity<StartProcessResponseDTO> startProcess(
            @Valid @RequestBody final StartProcessDTO request) {
        log.info("Start BPM process: '{}'", request.getProcessName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.businessService.startProcess(request));
    }

    @WithSpan
    @PostMapping(path = "wake-up-signal")
    public ResponseEntity<Void> wakeUpSignal(@Valid @RequestBody final WakeUpSignalDTO request) {
        log.info("Wake Up signal: '{}'", request.getSignalName());
        this.businessService.wakeUpSignal(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
