package com.demo.logging.entities;

import java.util.Map;

public class MyLogEnty {

    private Map<String,String> labels;
    private Map<String,String> jsonPayload;


    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Map<String, String> getJsonPayload() {
        return jsonPayload;
    }

    public void setJsonPayload(Map<String, String> jsonPayload) {
        this.jsonPayload = jsonPayload;
    }
}
