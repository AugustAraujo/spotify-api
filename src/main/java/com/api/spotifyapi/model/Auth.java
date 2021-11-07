package com.api.spotifyapi.model;

public class Auth {
    private String authURL;

    public Auth(String authURL) {
        this.authURL = authURL;
    }

    public String getAuthURL() {
        return authURL;
    }

    public void setAuthURL(String authURL) {
        this.authURL = authURL;
    }

}
