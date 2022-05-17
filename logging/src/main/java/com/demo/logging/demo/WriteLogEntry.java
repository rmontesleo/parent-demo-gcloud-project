package com.demo.logging.demo;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.JsonPayload;
import com.google.cloud.logging.Severity;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;

public class WriteLogEntry {

    public static void main(String[] args) throws Exception {
        // TODO(developer): Optionally provide the logname as an argument
        String logName = "test-log";

        // Instantiates a client
        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {
            Map<String, String> payload =
                    ImmutableMap.of(
                            "name", "King Arthur", "quest", "Find the Holy Grail", "favorite_color", "Blue");
            LogEntry entry =
                    LogEntry.newBuilder(JsonPayload.of(payload))
                            .setSeverity(Severity.INFO)
                            .setLogName(logName)
                            .setResource(MonitoredResource.newBuilder("global").build())
                            .build();

            logging.write(Collections.singleton(entry));
        }
        System.out.printf("Wrote to %s\n", logName);
    }
}