package com.puertomorelosapp.puertomorelosapp.Models.Request;

/**
 * Created by rudielavilaperaza on 8/11/17.
 */

public class NewUser {

    private String email;
    private String imageURL;
    private String provider;
    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
