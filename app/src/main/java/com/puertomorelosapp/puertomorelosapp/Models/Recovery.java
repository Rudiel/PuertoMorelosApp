package com.puertomorelosapp.puertomorelosapp.Models;

import android.content.Context;

/**
 * Created by rudielavilaperaza on 6/9/17.
 */

public class Recovery {

    private String Email;

    private Context context;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
