package com.demo.logging.service;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JsonParserService {

    private final File jsonFile;

    public static File getJsonFileFromClassPath(String filename ){
        File jsonFile = new File(ClassLoader.getSystemResource(filename).getFile() );
        return  jsonFile;
    }


    public JsonParserService(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public JSONObject getJsonObject(){
        JSONParser jsonParser = new JSONParser();
        try( FileReader reader = new FileReader(this.jsonFile) ){
            Object obj = jsonParser.parse(reader);
            return  (JSONObject)obj;
        } catch (FileNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray getJsonArray(){
        JSONParser jsonParser = new JSONParser();
        try( FileReader reader = new FileReader(this.jsonFile) ){
            Object obj = jsonParser.parse(reader);
            return  (JSONArray)obj;
        } catch (FileNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String,String> getJsonMap( JSONObject json, String label){
        return (Map<String,String>) json.get(label);
    }

    public  <T> T getObjectFromJson(JSONObject json, Class<T> target ){
        Gson gson = new Gson();
        return gson.fromJson( json.toJSONString(),  target  );
    }

}
