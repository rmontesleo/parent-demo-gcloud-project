package com.demo.logging.entities;

import com.google.cloud.logging.Severity;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
public class LogServiceEntry {

    private Map<String,String> labels;

    private Map<String,String> jsonPayload;

    private Map<String,String> operation;

    private LogServiceResource resource;

    private Severity severity;

    private String textPayload;


}
