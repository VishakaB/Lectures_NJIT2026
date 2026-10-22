package com.exampletest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;

public class Databasefile{
private Connection dbConnection = null;
private static Databasefile dbInstance = null;

//single instance of database that will be used through the application
//only one request to reach our database at a certain time //use synchronized for that
public static synchronized Databasefile getInstance() {
    if (dbInstance==null) {
        //create a new db instance
        dbInstance = new Databasefile();
    }
    return dbInstance;
}

//constructor
private Databasefile(){
//open a database
//give a name to our database
String dbname = "anyDatabase.db";
//path to our database
String dbpath = "jdbc:sqlite:" + dbname;

//to avoid sql exceptions we will use try  catch blocks
    try {
        dbConnection = DriverManager.getConnection(dbpath);
        initializetable();
    } catch (Exception e) {
        System.err.println("database connection error: "+e.getMessage());
    }
}

public void initializetable(){
//initialize tables
//use keyword IF NOT EXISTS to prevent the table getting created again
String createtable = "CREATE TABLE IF NOT EXISTS users (user varchar(50) NOT NULL, message varchar(50) NOT NULL, timestamp varchar(50) NOT NULL)";

        try {
            Statement createstatement = dbConnection.createStatement();
            createstatement.execute(createtable);
            System.out.println("Database table successfully created");
            createstatement.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("create table sql exception: "+ e.getMessage());
        }
}
//create a method to store data in the tables 
public void setMessage(JSONObject message) throws SQLException{
    //create a sql statement
    //this sql statement is a dynamic statement with variables
    String storedata = "INSERT INTO users (user, message, timestamp) VALUES (?, ?, ?)";
    //define the values of the variables using preparedStatement

    try {
        PreparedStatement ps = dbConnection.prepareStatement(storedata);
        ps.setString(1, message.getString("user"));
        ps.setString(2, message.getString("message"));
        //store timestamp information
        ps.setString(3, message.getString("timestamp"));
        //for inserting values we use execute() method
        ps.execute();
        ps.close();

    } catch (Exception e) {
        // TODO: handle exception
        System.err.println("error in storing data"+e.getMessage());
    }
    
}

//get data from database and return as a JSONObject
public JSONObject getMessage(String name_) throws SQLException{
    JSONObject obj = new JSONObject();
    String getdata = "SELECT user, message, timestamp FROM users WHERE user = ?";
    try {
        //create a prepared statement object ps
        PreparedStatement ps = dbConnection.prepareStatement(getdata);
        //set the variables in the SQL statement using prepared statement object ps
        ps.setString(1, name_);
       
        //save the output of the query in a resultset object
        ResultSet rs = ps.executeQuery();
        //process the rs
        while (rs.next()){
           obj.put("user", rs.getString("user"));
           obj.put("message", rs.getString("message"));
           obj.put("timestamp", rs.getString("timestamp"));
        }
        
    } catch (Exception e) {
        // TODO: handle exception
        System.err.println("get messages error: "+e.getMessage());
    } 
    return obj;
}

public void close(){
   try {
        dbConnection.close();
   } catch (Exception e) {
    // TODO: handle exception
    System.err.println("close database failed: "+e.getMessage());
   }
}

}