package com.example;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;

import javax.swing.text.html.HTML;

import org.json.JSONObject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class externalAPI implements HttpHandler{

    private String external = "https://pokeapi.co/api/v2/pokemon/";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    public JSONObject getPokemon(String pokemon) throws IOException, InterruptedException{
        //create httpclient obj that will access the external api: pokemon
        HttpClient newclient = HttpClient.newHttpClient();

        //define the data format that we want as the response
        String dataformat = "application/json";
        //define the external api url
        final String externalurl = "https://pokeapi.co/api/v2/pokemon/"+pokemon;
        //create the URI that is corresponding to the url
        URI uri_example = URI.create(externalurl);
        //the above are used as input argument into create HTTP request object
        HttpRequest request = HttpRequest.newBuilder().uri(uri_example).header("Accept", dataformat).GET().build();

        //send request using the httpclient object that we created earlier
        //define the response
        //HTTPResponse in the form of a String array
        HttpResponse<String> response = newclient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        JSONObject obj = new JSONObject(response.body());
        return obj;
    }

    // try
    // HttpResponse response = newclient.send(request, HttpResponse.BodyHandlers.toString());


}
