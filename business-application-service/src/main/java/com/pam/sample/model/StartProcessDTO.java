package com.pam.sample.model;

import java.util.Map;
import lombok.Data;

@Data
public class StartProcessDTO {
    private String processName;
    private String kjar;
    private Map<String, Object> parameters;
}
