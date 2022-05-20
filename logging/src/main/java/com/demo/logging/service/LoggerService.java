package com.demo.logging.service;

import com.demo.logging.dto.LogDataDto;
import com.demo.logging.entities.LogServiceEntry;
import com.demo.logging.entities.LogServiceResource;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.*;
import com.google.cloud.logging.Payload.JsonPayload;
import com.google.cloud.logging.Payload.StringPayload;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoggerService {

    private LogDataDto prepareData(LogServiceEntry logEntry ){

        Map<String, String> payLoad = Optional.ofNullable(logEntry.getJsonPayload() ).orElse( new HashMap<>() );

        String textPayLoad = Optional.ofNullable( logEntry.getTextPayload() ).orElse("Hello-world! ");

        Map<String, String> labels = Optional.ofNullable( logEntry.getLabels() ).orElse( new HashMap<>() );

        Optional<LogServiceResource> optionalResource =  Optional.ofNullable(logEntry.getResource());

        String type =  optionalResource.map(  resource ->  resource.getType() ).orElse("global");

        Map<String,String> resourceLabels = optionalResource.map( resource-> resource.getLabels() ).orElse( new HashMap<>() );

        Operation operation = Optional.ofNullable(logEntry.getOperation() )
                .map( map -> Operation.newBuilder(map.get("id"), map.get("producer")).build()  )
               .orElse(null);

        Severity severity = Optional.ofNullable(logEntry.getSeverity() ).orElse( Severity.INFO );


        LogDataDto dto = new LogDataDto(
          payLoad, textPayLoad, labels, type, resourceLabels, operation, severity
        );

        return dto;
    }


    public void writeJsonLogEntry(String logName, LogServiceEntry logEntry, HttpRequest httpRequest ) throws Exception {

        LogDataDto dto = prepareData( logEntry );

        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {
            LogEntry entry =
                    LogEntry.newBuilder( JsonPayload.of( dto.jsonPayLoad() ))
                            .setSeverity( dto.severity() )
                            .setLogName(logName )
                            .setLabels( dto.labels() )
                            .setResource(MonitoredResource.newBuilder( dto.type() ).setLabels(  dto.resourceLabels() ).build()  )
                            .setOperation( dto.operation() )
                            .setHttpRequest(httpRequest)
                            .build();
            logging.write(Collections.singleton(entry));
        }
    }

    public void writeTextLogEntry(String logName, LogServiceEntry logEntry, HttpRequest httpRequest ) throws Exception {

        LogDataDto dto = prepareData( logEntry);

        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {
            LogEntry entry =
                    LogEntry.newBuilder( StringPayload.of( dto.textPayLoad() ))
                            .setSeverity( dto.severity() )
                            .setLogName(logName )
                            .setLabels( dto.labels() )
                            .setResource(MonitoredResource.newBuilder( dto.type() ).setLabels(  dto.resourceLabels() ).build()  )
                            .setOperation( dto.operation() )
                            .setHttpRequest(httpRequest)
                            .build();
            logging.write(Collections.singleton(entry));
        }
    }

}