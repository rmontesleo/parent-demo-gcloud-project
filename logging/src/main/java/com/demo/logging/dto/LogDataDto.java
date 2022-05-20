package com.demo.logging.dto;

import java.util.Map;

import com.google.cloud.logging.Operation;
import com.google.cloud.logging.Severity;

public record LogDataDto(
    Map<String,String> jsonPayLoad,
    String textPayLoad,
    Map<String,String> labels,
    String type,
    Map<String,String> resourceLabels,
    Operation operation,
    Severity severity
) {
}
