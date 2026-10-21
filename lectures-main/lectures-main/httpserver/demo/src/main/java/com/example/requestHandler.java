package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
public class requestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request = exchange.getRequestMethod();
        if(!request.equalsIgnoreCase("GET")){
            sendresponse(exchange, 200, "Have a nice day");
        } else {
            sendresponse(exchange, 404, "qNot successful");
        }
    }

    private void sendresponse(HttpExchange exchange, int statuscode, String response){

        //Client is sending a request to "/noodle"
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
