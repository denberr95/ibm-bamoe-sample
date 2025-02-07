package com.pam.sample.model;

import lombok.Data;

@Data
public class StartProcessResponseDTO {
    
    private String correlationKey;
    private Long processInstanceId;
}
