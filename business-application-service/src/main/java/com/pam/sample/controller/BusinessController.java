package com.pam.sample.controller;

import com.pam.sample.model.StartProcessDTO;
import com.pam.sample.model.StartProcessResponseDTO;
import com.pam.sample.model.WakeUpSignalDTO;
import com.pam.sample.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @WithSpan
    @PostMapping(path = "start-process")
    public ResponseEntity<StartProcessResponseDTO> startProcess(
            @RequestBody final StartProcessDTO request) {
        log.info("Start BPM process: '{}'", request.getProcessName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.businessService.startProcess(request));
    }

    @WithSpan
    @PostMapping(path = "wake-up-signal")
    public ResponseEntity<Void> wakeUpSignal(@RequestBody final WakeUpSignalDTO request) {
        log.info("Wake Up signal: '{}'", request.getSignalName());
        this.businessService.wakeUpSignal(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
