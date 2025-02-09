package com.pam.sample.model;

import java.util.Map;
import java.util.Objects;

public class StartProcessDTO {

    private String processName;
    private String kjar;
    private Map<String, Object> parameters;

    /**
     * 
     */
    public StartProcessDTO() {}

    /**
     * @param processName
     * @param kjar
     * @param parameters
     */
    public StartProcessDTO(String processName, String kjar, Map<String, Object> parameters) {
        this.processName = processName;
        this.kjar = kjar;
        this.parameters = parameters;
    }

    /**
     * @return the processName
     */
    public String getProcessName() {
        return this.processName;
    }

    /**
     * @param processName the processName to set
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    /**
     * @return the kjar
     */
    public String getKjar() {
        return this.kjar;
    }

    /**
     * @param kjar the kjar to set
     */
    public void setKjar(String kjar) {
        this.kjar = kjar;
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
        return Objects.hash(this.processName, this.kjar, this.parameters);
    }

    @Override
    public String toString() {
        return "StartProcessDTO [processName=" + this.processName + ", kjar=" + this.kjar
                + ", parameters=" + this.parameters + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        StartProcessDTO other = (StartProcessDTO) obj;
        return Objects.equals(this.processName, other.processName)
                && Objects.equals(this.kjar, other.kjar)
                && Objects.equals(this.parameters, other.parameters);
    }
}
