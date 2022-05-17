package com.demo.logging.service;

import com.demo.logging.entities.LogServiceEntry;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoggerService {

    public void writeLogEntry(  String logName, LogServiceEntry logEntry,  Severity severity, String monitoredResource ) throws Exception {

        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {

            Optional<Map<String, String>> payloadOptional =  Optional.of(logEntry.getJsonPayload());
            Map<String, String> payLoad = payloadOptional.map( data -> data  ).orElse( new HashMap<>() );

            Optional<Map<String,String>> labelsOptional = Optional.of( logEntry.getLabels() );
            Map<String, String> labels = labelsOptional.map( data -> data ).orElse( new HashMap<>() );

            Optional<Map<String, String>> operationMap =  Optional.of(logEntry.getOperation() );
            Operation operation = operationMap
                    .map( map -> Operation.newBuilder(map.get("id"), map.get("producer")).build()  )
                    .orElse(  Operation.newBuilder("id", "producer").build()  );

            LogEntry entry =
                    LogEntry.newBuilder(Payload.JsonPayload.of(payLoad))
                            .setSeverity( severity )
                            .setLogName(logName)
                            .setResource(MonitoredResource.newBuilder( monitoredResource ).build())
                            .setLabels(labels)
                            .setOperation(operation)
                            .build();

            logging.write(Collections.singleton(entry));

        }
    }

}