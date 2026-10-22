package com.exampletest;
import java.lang.String;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;


public class App {
    public static void main(String[] args) {
    System.out.println("database demo");

    Databasefile db = Databasefile.getInstance();
    String jsonString = "{\r\n" + //
                "    \"user\": \"Ching\",\r\n" + //
                "    \"message\": \"It is a busy day!\",\r\n" + //
                "}";
    JSONObject obj = new JSONObject(jsonString);
    
    try {
        //use the db instance here and apply the method defined in db class
        db.setMessage(obj);
    } catch (Exception e) {
        // TODO: handle exception
        System.err.println("data not stored!: "+ e.getMessage());
    }

    try {
        JSONObject result_obj = db.getMessage("Ching");
        System.out.println("result object: "+result_obj);
    } catch (Exception e) {
        // TODO: handle exception
    }

    //close database
   db.close();

    }
}