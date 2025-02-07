package com.pam.sample.model;

import java.util.Map;
import lombok.Data;

@Data
public class WakeUpSignalDTO {
    
    private String signalName;
    private Long processInstanceId;
    private String correlationKey;
    private Map<String, Object> parameters;
}
