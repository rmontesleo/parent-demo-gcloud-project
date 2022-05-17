package com.demo.logging.service;

import com.demo.logging.entities.LogServiceEntry;
import com.demo.logging.entities.LogServiceResource;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoggerService {

    public void writeLogEntry(  String logName, LogServiceEntry logEntry ) throws Exception {

        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {

            Map<String, String> payLoad = Optional.ofNullable(logEntry.getJsonPayload() ).orElse( new HashMap<>() );

            Map<String, String> labels = Optional.ofNullable( logEntry.getLabels() ).orElse( new HashMap<>() );

            Optional<LogServiceResource> optionalResource =  Optional.ofNullable(logEntry.getResource());
            String type =  optionalResource.map(  resource ->  resource.getType() ).orElse("global");
            Map<String,String> resourceLabels = optionalResource.map( resource-> resource.getLabels() ).orElse( new HashMap<>() );

            Operation operation = Optional.ofNullable(logEntry.getOperation() )
                    .map( map -> Operation.newBuilder(map.get("id"), map.get("producer")).build()  )
                    .orElse(null);

            Severity severity = Optional.ofNullable(logEntry.getSeverity() ).orElse( Severity.INFO );

            System.out.println( "severitiy is " + severity );

            LogEntry entry =
                    LogEntry.newBuilder(Payload.JsonPayload.of(payLoad))
                            .setSeverity( severity )
                            .setLogName(logName)
                            .setLabels(labels)
                            .setResource(MonitoredResource.newBuilder( type ).setLabels(  resourceLabels ).build()  )
                            .setOperation(operation)
                            .build();

            logging.write(Collections.singleton(entry));

        }
    }

}