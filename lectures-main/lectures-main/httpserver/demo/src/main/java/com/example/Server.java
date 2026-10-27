package com.example;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.concurrent.Executors;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;

//author Vishaka Basnayake
//last update: 27th October
public class Server 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println(args.length);
        int portnumber = 8001;
        //creating a database instance called db, using getInstance method in db class
        Database_123 db = Database_123.getInstance();
        HttpsServer server = HttpsServer.create(new InetSocketAddress(portnumber),0);
        server.createContext("/registration", new registrationHandler());
        server.createContext("/info",new requestHandler(db));
        //create keystore
        //keytool -genkey -alias alias -keyalg RSA -keystore keystore.jks -keysize 2048
        String keystore_path = args[0];
        String password = args[1];

        try {
            //create an instance of the keystore as an object
            KeyStore ks = KeyStore.getInstance("JKS");
            //add the keystore file details to the ks object

            //convert the password to a character array
            char [] passphrase = password.toCharArray();
            //add the keystore file details to the ks object
            ks.load(new FileInputStream(keystore_path),passphrase);

            //create a keystoremanager factory to store the private keys
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, passphrase);

            //create a trustmanagerfactory to store the public keys
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            SSLContext ssl = SSLContext.getInstance("TLS");
            ssl.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            //create HTTPS configurations
            server.setHttpsConfigurator(new HttpsConfigurator(ssl){
                public void configure (HttpsParameters params){

                    InetSocketAddress remote = params.getClientAddress();

                    SSLContext c = getSSLContext();

                    //get the default ssl parameters
                    SSLParameters sslParameters = c.getDefaultSSLParameters();

                    params.setSSLParameters(sslParameters);
                }
            });

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("error: "+ e.getMessage());
        }
        //create deafult, same thread, for server excution
        //server.setExecutor(null);
        //create threadpool having number of threads based on the request demand
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server started on port: "+ "https://localhost:"+portnumber);
    }

}
