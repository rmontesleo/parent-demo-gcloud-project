package com.demo.logging;

import com.demo.logging.entities.LogServiceEntry;
import com.demo.logging.service.JsonParserService;
import com.demo.logging.service.LoggerService;
import com.google.cloud.logging.HttpRequest;
import org.json.simple.JSONObject;

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

        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .setRequestUrl("www.example.com")
                        .setRequestMethod(HttpRequest.RequestMethod.GET) // Supported method GET,POST,PUT,HEAD
                        .setStatus(200)
                        .build();

        LoggerService loggerService = new LoggerService();
        loggerService.writeJsonLogEntry( "test-json-log-v11", logServiceEntry, httpRequest );
        loggerService.writeJsonLogEntry( "test-json-log-v11", logServiceEntry, null);

        loggerService.writeTextLogEntry( "test-text-log-v10", logServiceEntry, null );

    }
}
