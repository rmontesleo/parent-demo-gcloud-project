package com.demo.logging.entities;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
public class LogServiceEntry {

    private Map<String,String> labels;

    private Map<String,String> jsonPayload;

    private Map<String,String> operation;


}
