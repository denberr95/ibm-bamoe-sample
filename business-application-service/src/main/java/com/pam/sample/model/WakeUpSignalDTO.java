package com.pam.sample.model;

import java.util.Map;
import java.util.Objects;

public class WakeUpSignalDTO {

    private String signalName;
    private Long processInstanceId;
    private String correlationKey;
    private Map<String, Object> parameters;

    /**
     * 
     */
    public WakeUpSignalDTO() {}

    /**
     * @param signalName
     * @param processInstanceId
     * @param correlationKey
     * @param parameters
     */
    public WakeUpSignalDTO(String signalName, Long processInstanceId, String correlationKey,
            Map<String, Object> parameters) {
        this.signalName = signalName;
        this.processInstanceId = processInstanceId;
        this.correlationKey = correlationKey;
        this.parameters = parameters;
    }

    /**
     * @return the signalName
     */
    public String getSignalName() {
        return this.signalName;
    }

    /**
     * @param signalName the signalName to set
     */
    public void setSignalName(String signalName) {
        this.signalName = signalName;
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
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.signalName, this.processInstanceId, this.correlationKey,
                this.parameters);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        WakeUpSignalDTO other = (WakeUpSignalDTO) obj;
        return Objects.equals(this.signalName, other.signalName)
                && Objects.equals(this.processInstanceId, other.processInstanceId)
                && Objects.equals(this.correlationKey, other.correlationKey)
                && Objects.equals(this.parameters, other.parameters);
    }

    @Override
    public String toString() {
        return "WakeUpSignalDTO [signalName=" + this.signalName + ", processInstanceId="
                + this.processInstanceId + ", correlationKey=" + this.correlationKey
                + ", parameters=" + this.parameters + "]";
    }
}
