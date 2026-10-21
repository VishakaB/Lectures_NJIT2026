package com.example;
import java.sql.Connection;


public class Database_123 {
    //create an instance of the database // single instance throughout application
    private static Database_123 dbInstance = null;
    private Connection dbconnection = null;

    //constructor
    public Database_123 (){
        //open database instance
        //continue 

    }

    //create an instance of the database // single instance of db needed
    //synchronized used to allow only one request access to the database at a certain time
    private static synchronized Database_123 getInstance(){

        //if dbinstance not available // create a new instance
        if(dbInstance==null){
            dbInstance = new Database_123();
        }

        return dbInstance;
    }


    public boolean storeUserdata(String name, String message){
        //return boolean true or false
        //if the data was stored successfully return true

        //if data was not stored successfully return false

    }
}