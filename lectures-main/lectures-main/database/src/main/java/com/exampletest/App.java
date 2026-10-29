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

   //use a new database file
   Database_joins db_joins = Database_joins.getInstance();
    String jsonUser = "{\r\n" + //
                "    \"user\": \"Jung\",\r\n" + //
                "    \"password\": \"1234\"\r\n" + //
                "}";

    String jsonMessage = "{\r\n" + //
                "    \"user\" : \"Jung\",\r\n" + //
                "    \"message\": \"May this be a better day!\"\r\n" + //
                "}";

    JSONObject Userobj = new JSONObject(jsonUser);
    try {
        db_joins.setUser(Userobj);
    } catch (Exception e) {
        // TODO: handle exception
        System.err.println("error: "+ e);
    }

    JSONObject messageobj = new JSONObject(jsonMessage);
    try {
        db_joins.setMessage_joins(messageobj);
    } catch (Exception e) {
        // TODO: handle exception
        System.err.println("error: "+ e);
    }

    try {
        JSONObject resultOBJ  = db_joins.getMessage_joins("Jung");
        System.out.println("the final result is: "+resultOBJ);
    } catch (Exception e) {
        // TODO: handle exception
        System.err.println("error: "+ e);
    }


    }
}