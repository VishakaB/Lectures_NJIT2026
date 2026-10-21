package com.exampletest;
import java.lang.String;
import java.sql.SQLException;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) {
    System.out.println("database demo");
    Databasefile db = Databasefile.getInstance();
    String jsonString = "{\r\n" + //
    " \"user\": \"Peng\",\r\n" + //
    " \"message\":\"It is raining today!\"\r\n" + //
    "} \r\n" + //
    "";
    JSONObject obj = new JSONObject(jsonString);
 
    try {
        //use the db instance here and apply the method define db class
        db.setMessage(obj);
    } catch (Exception e) {
        // TODO: handle exception
        System.err.println("data not stored!: "+ e.getMessage());
    }

    System.out.println(obj);
    }
}