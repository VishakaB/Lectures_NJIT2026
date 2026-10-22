package com.exampletest;
import java.lang.String;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;

import com.exampletest.Utils.timeformat;


public class App {
    public static void main(String[] args) {
    System.out.println("database demo");

    Databasefile db = Databasefile.getInstance();
    String jsonString = "{\r\n" + //
                "    \"user\": \"Ching\",\r\n" + //
                "    \"message\": \"It is a busy day!\",\r\n" + //
                "    \"timestamp\": \"2026-10-21T06:27:06.616Z\"\r\n" + //
                "}";
    JSONObject obj = new JSONObject(jsonString);
    
    try {
        //use the db instance here and apply the method defined in db class
        //check if the timestamp in the message follows the UTC time format

        timeformat tf = new timeformat();
        //if the timestamp in the user message is following the UTC format, only then store messages in the database
        if(tf.checktimeformat(obj)){
            //store messages in the database
            System.out.println("storing message in the database");
            db.setMessage(obj);
        }


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

//lets try to find the time information 
ZonedDateTime time_now = ZonedDateTime.now();
System.out.println("time now: (general) "+time_now);

//convert the current time to a standard UTC format
ZonedDateTime time_nowUTC = ZonedDateTime.now(ZoneId.of("UTC")); 
System.out.println("time in UTC format: "+time_nowUTC);

    }
}