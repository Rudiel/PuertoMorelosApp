package com.puertomorelosapp.puertomorelosapp.Models;

import android.content.Context;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Register {

    private String Username;
    private String Email;
    private String Password;
    private Context context;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
