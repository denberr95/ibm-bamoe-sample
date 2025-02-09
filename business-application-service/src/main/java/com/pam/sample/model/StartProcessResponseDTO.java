package com.pam.sample.model;

import java.util.Objects;

public class StartProcessResponseDTO {

    private String correlationKey;
    private Long processInstanceId;

    /**
     * 
     */
    public StartProcessResponseDTO() {}

    /**
     * @param correlationKey
     * @param processInstanceId
     */
    public StartProcessResponseDTO(String correlationKey, Long processInstanceId) {
        this.correlationKey = correlationKey;
        this.processInstanceId = processInstanceId;
    }

    /**
     * @return the correlationKey
     */
    public String getCorrelationKey() {
        return this.correlationKey;
    }

    /**
     * @param correlationKey the correlationKey to set
     */
    public void setCorrelationKey(String correlationKey) {
        this.correlationKey = correlationKey;
    }

    /**
     * @return the processInstanceId
     */
    public Long getProcessInstanceId() {
        return this.processInstanceId;
    }

    /**
     * @param processInstanceId the processInstanceId to set
     */
    public void setProcessInstanceId(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.correlationKey, this.processInstanceId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        StartProcessResponseDTO other = (StartProcessResponseDTO) obj;
        return Objects.equals(this.correlationKey, other.correlationKey)
                && Objects.equals(this.processInstanceId, other.processInstanceId);
    }

    @Override
    public String toString() {
        return "StartProcessResponseDTO [correlationKey=" + this.correlationKey
                + ", processInstanceId=" + this.processInstanceId + "]";
    }
}
