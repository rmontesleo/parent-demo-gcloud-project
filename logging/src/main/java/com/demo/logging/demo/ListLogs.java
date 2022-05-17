package com.demo.logging.demo;

import com.google.api.gax.paging.Page;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;

public class ListLogs {

    public static void main(String... args) throws Exception {

        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {

            // List all log names
            Page<String> logNames = logging.listLogs();
            while (logNames != null) {
                for (String logName : logNames.iterateAll()) {
                    System.out.println(logName);
                }
                logNames = logNames.getNextPage();
            }
        }
    }
}