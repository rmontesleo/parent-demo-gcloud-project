package com.demo.logging.demo;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.*;
import com.google.cloud.logging.Payload.JsonPayload;
import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.Map;

public class WriteLogEntryV2 {

    public static void main(String[] args) throws Exception {
        // TODO(developer): Optionally provide the logname as an argument
        String logName = "test-log";

        // Instantiates a client
        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {
            Map<String, String> payload =
                    ImmutableMap.of(
                            "name", "King Arthur", "quest", "Find the Holy Grail", "favorite_color", "Blue");

            Map<String,String> labels =
                    ImmutableMap.of( "privider1", "GCP", "provider2", "Azure" );

            Operation operation =
                    Operation.newBuilder( "abc", "producer").build();



            LogEntry entry =
                    LogEntry.newBuilder(JsonPayload.of(payload))
                            .setSeverity(Severity.INFO)
                            .setLogName(logName)
                            .setResource(MonitoredResource.newBuilder("global").build())
                            .setLabels( labels  )
                            .addLabel("provider3", "AWS")
                            .setOperation( operation )
                            .build();

            logging.write(Collections.singleton(entry));
        }
        System.out.printf("Wrote to %s\n", logName);
    }
}