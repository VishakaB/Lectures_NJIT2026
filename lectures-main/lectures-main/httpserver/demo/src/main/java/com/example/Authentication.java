package com.example;

import java.util.List;
import java.util.ArrayList;
import com.sun.net.httpserver.BasicAuthenticator;

public class Authentication extends BasicAuthenticator {

    List<String> userlist = new ArrayList<String>();

    public Authentication(String realm) {
        super(realm);
        // TODO Auto-generated constructor stub
    }

    // check username and password
    @Override
    public boolean checkCredentials(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkCredentials'");
    }

    // register new user
    {
        // if user exists return false
        //

        // if the user does not exist register the user

    }

}
