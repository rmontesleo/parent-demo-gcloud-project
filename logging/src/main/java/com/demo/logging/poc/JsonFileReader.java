package com.demo.logging.poc;

import com.demo.logging.entities.MyLogEnty;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public class JsonFileReader {

    public static void main(String[] args) throws IOException, ParseException {

        File file = new File(ClassLoader.getSystemResource("sample01.json").getFile());

        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;


        try( FileReader reader = new FileReader(file) ){


            System.out.println("ok..");
            Object obj = jsonParser.parse(reader);
            json = (JSONObject)obj;
            System.out.println(json);

            Map<String,String> labels  = (Map<String,String>)json.get("labels");
            System.out.println( labels.keySet() );

            Map<String,String> jsonPayload  = (Map<String,String>)json.get("jsonPayload");
            System.out.println( jsonPayload.keySet() );


            System.out.println("##############");

            Gson gson = new Gson();
            MyLogEnty myLogEnty = gson.fromJson( json.toJSONString(),MyLogEnty.class );
            System.out.println( myLogEnty.getJsonPayload().keySet() );
            System.out.println( myLogEnty.getLabels() );

            System.out.println("##############");

            MyLogEnty myLogEnty2 = getObjectFromJson( json, MyLogEnty.class  );
            System.out.println( myLogEnty2.getJsonPayload().keySet() );
            System.out.println( myLogEnty2.getLabels() );



        }

    }

    private static <T> T getObjectFromJson(JSONObject json, Class<T> target ){
        Gson gson = new Gson();
        return gson.fromJson( json.toJSONString(),  target  );
    }

}
