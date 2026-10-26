package com.example;
import java.io.BufferedReader;
import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class registrationHandler implements HttpHandler {

    public registrationHandler(){

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub
        String request = exchange.getRequestMethod();
        if(!request.equalsIgnoreCase("POST")){
            sendresponse(exchange, 200, "registration successful");
        } else {
            sendresponse(exchange, 404, "registration not successful");
        }

        String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
        if (contentType == null || !contentType.toLowerCase().startsWith("application/json"))
        {
             sendresponse(exchange, 400, "unsupported data format");
        }

        //get the data inside the Request
        InputStream stream = exchange.getRequestBody();
        String requestbody = new BufferedReader(new InputStreamReader(stream,StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        stream.close();
        
        //String mockrequestbody =; 

        //Jsonobject create
        
        // check JSON contents
        //check authorization

    }

    private void sendresponse(HttpExchange exchange, int statuscode, String response){
        // add logic here
    }
    
}
