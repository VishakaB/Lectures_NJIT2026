package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.example.utils.*;

public class requestHandler implements HttpHandler {

    public requestHandler(){
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request = exchange.getRequestMethod();
        int code;
        String response;
        String contentType;
        Headers headers = exchange.getRequestHeaders();
        if(request.equalsIgnoreCase("GET")){
            System.out.println("received GET message");
            sendresponse(exchange, 400, "Received GET message");
        } else if (request.equalsIgnoreCase("POST")) {
            System.out.println("received POST or other message");

            // if (headers.containsKey("Content-Type")) {
            //     contentType = headers.get("Content-Type").get(0);
            // } else {
            //     code = 411; // Length Required
            //     response = "No content type in request";
            //     sendresponse(exchange, code, response);
            //     return;
            // }

            // if (contentType.equalsIgnoreCase("application/json")) {
            //     InputStream stream = exchange.getRequestBody();
            //     String requestjson = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
            //             .lines().collect(Collectors.joining("\n"));

            //     if (requestjson.isEmpty()) {
            //         code = 412; // Precondition Failed
            //         response = "No text to post!";
            //         sendresponse(exchange, code, response);
            //         return;
            //     }
            //     // 
            //      //sendresponse(exchange, 404, "Not successful");
            //     if ((requestjson.trim().startsWith("{"))) {
            //         // Single JSONObject
                   

              

            //     }
              
            
        } else {
            sendresponse(exchange, 404, "method not allowed!");
        }
    }

    private void sendresponse(HttpExchange exchange, int statuscode, String response){

        //Client is sending a request
        //define sendresponse method
        //method should convert String response to an array of bytes using UTF-8 encoding
        byte [] byteArray = response.getBytes(StandardCharsets.UTF_8);

        // create an instance of a response header using exchange instance
        // //set the header parameters by setting Content-Type to "application/json; charset=UTF-8"
        // headers.set("Content-Type","plain/text; charset=UTF-8");
        try {
                    // //response headers via exchange instance
            exchange.sendResponseHeaders(statuscode, byteArray.length);

            //create an instance of OutputStream, empty
            OutputStream os = exchange.getResponseBody();

            //fill os with data and send to client
            os.write(byteArray);
            os.flush();
            //close output stream
            os.close();
        } catch (Exception e) {
            System.err.println("error in send response: "+ e);
        }

        
    }
    
}
