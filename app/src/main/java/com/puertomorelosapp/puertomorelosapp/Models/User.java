package com.puertomorelosapp.puertomorelosapp.Models;

import android.content.Context;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class User {

    private String email;
    private String password;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
