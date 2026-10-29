package com.exampletest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;

public class Database_joins {
private Connection dbConnection = null;
private static Database_joins dbInstance = null;

//single instance of database that will be used through the application
//only one request to reach our database at a certain time //use synchronized for that
public static synchronized Database_joins getInstance() {
    if (dbInstance==null) {
        //create a new db instance
        dbInstance = new Database_joins();
    }
    return dbInstance;
}

//constructor
private Database_joins(){
//open a database
//give a name to our database
String dbname = "newDatabase.db";
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
String createtable_users = "CREATE TABLE IF NOT EXISTS users_table (userid INTEGER PRIMARY KEY AUTOINCREMENT, user varchar(50) NOT NULL, password text NOT NULL)";

        try {
            Statement createstatement = dbConnection.createStatement();
            createstatement.execute(createtable_users);
            System.out.println("Database table successfully created");
            createstatement.close();
        } catch (Exception e) {
            System.err.println("create table sql exception: "+ e.getMessage());
        }

String createtable_messages = "CREATE TABLE IF NOT EXISTS messages_table (userid INTEGER NOT NULL, message varchar(50) NOT NULL, FOREIGN KEY (userid) REFERENCES users_table (userid)) ";

        try {
            Statement createstatement = dbConnection.createStatement();
            createstatement.execute(createtable_messages);
            System.out.println("Database message successfully created");
            createstatement.close();
        } catch (Exception e) {
            System.err.println("create table sql exception: "+ e.getMessage());
        }

}

//create a method to store data in the tables
//add synchronized keyword to allow only one request at a time 
public synchronized void setUser(JSONObject user) throws SQLException{
    //create a sql statement
    //define the values of the variables using preparedStatement
    String username = user.getString("user");
   System.out.println("username: " +username);
    String password = user.getString("password");
    final String sql = "INSERT INTO users_table (user, password) VALUES (?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.execute();
        } catch (SQLException e) {
            // reference:https://www.geeksforgeeks.org/dbms/how-to-handle-duplicate-key-violations-in-jdbc/
            // Handle exceptions
            System.err.println("storing user data error: "+ e);
        }
}

//create a method to store data in the tables
//add synchronized keyword to allow only one request at a time 
public synchronized void setMessage_joins(JSONObject message) throws SQLException{
    //create a sql statement
    
    //define the values of the variables using preparedStatement
    String messages = message.getString("message");
    String username = message.getString("user");
    //find the userid
    int userid=-1;
    String useridquery = "SELECT userid FROM users_table WHERE users_table.user = ? ";
        try (PreparedStatement ps = dbConnection.prepareStatement(useridquery)) {
            ps.setString(1, username);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    userid= rs.getInt("userid");
                }
            }
        }
    //this sql statement is a dynamic statement with variables
    String storedata = "INSERT INTO messages_table (userid, message) VALUES (?, ?)";

    try {
        PreparedStatement ps2 = dbConnection.prepareStatement(storedata);
        ps2.setInt(1, userid);
        ps2.setString(2, message.getString("message"));
        //for inserting values we use execute() method
        ps2.execute();
        ps2.close();

    } catch (Exception e) {
        System.err.println("error in storing data"+e.getMessage());
    }
    
}

//get data from database and return as a JSONObject
public synchronized JSONObject getMessage_joins(String name_) throws SQLException{
    //find userid

    JSONObject obj = new JSONObject();
    String getdata = "SELECT message FROM messages_table JOIN users_table ON users_table.userid = messages_table.userid WHERE users_table.user = ? ";
    try {
        //create a prepared statement object ps
        PreparedStatement ps = dbConnection.prepareStatement(getdata);
        //set the variables in the SQL statement using prepared statement object ps
        ps.setString(1, name_);
       
        //save the output of the query in a resultset object
        ResultSet rs = ps.executeQuery();
        //process the rs
        while (rs.next()){
           obj.put("message", rs.getString("message"));
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