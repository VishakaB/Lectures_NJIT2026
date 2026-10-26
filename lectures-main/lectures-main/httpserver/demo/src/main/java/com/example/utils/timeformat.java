package com.example.utils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.json.JSONObject;

public class timeformat {

    //constructor 
    public timeformat(){

    }

    public boolean checktimeformat(JSONObject obj){
        if (obj.has("timestamp")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            try {
                ZonedDateTime time = ZonedDateTime.parse(obj.getString("timestamp"), formatter);
                if (!time.getOffset().equals(ZoneOffset.UTC)){
                    return false;
                } else {
                    return true;
                }
            } catch (DateTimeParseException e) {
                // TODO: handle exception
                System.err.println("erorr in parsing time: "+e.getMessage());
                return false;
            }
            
        } else {
            return false;
        }

    

    }
    
}
