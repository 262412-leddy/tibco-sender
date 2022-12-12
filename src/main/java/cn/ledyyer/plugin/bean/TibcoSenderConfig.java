package cn.ledyyer.plugin.bean;

public class TibcoSenderConfig {
    private String service;
    private String network;
    private String daemon;
    private String subjectName;
    private String timeout;
    private String fieldName;
    private String message;

    public TibcoSenderConfig(){}

    public TibcoSenderConfig(String service,
                             String network,
                             String daemon,
                             String subjectName,
                             String timeout,
                             String fieldName,
                             String message) {
        this.service = service;
        this.network = network;
        this.daemon = daemon;
        this.subjectName = subjectName;
        this.timeout = timeout;
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getDaemon() {
        return daemon;
    }

    public void setDaemon(String daemon) {
        this.daemon = daemon;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
