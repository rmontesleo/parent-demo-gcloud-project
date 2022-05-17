package com.demo.logging;

import com.demo.logging.entities.LogServiceEntry;
import com.demo.logging.service.JsonParserService;
import com.demo.logging.service.LoggerService;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.Severity;
import org.json.simple.JSONObject;

import java.io.File;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        JsonParserService  jsonParserService
                = new JsonParserService( JsonParserService.getJsonFileFromClassPath("sample01.json") );

        JSONObject jsonObject = jsonParserService.getJsonObject();

        LogServiceEntry logServiceEntry = jsonParserService.getObjectFromJson( jsonObject, LogServiceEntry.class );
        System.out.println( logServiceEntry );

        LoggerService loggerService = new LoggerService();
        loggerService.writeLogEntry( "test-log-v2", logServiceEntry, Severity.INFO, "global" );

    }
}
