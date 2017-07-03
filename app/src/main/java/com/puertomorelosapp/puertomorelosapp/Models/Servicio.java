package com.puertomorelosapp.puertomorelosapp.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by rudielavilaperaza on 7/3/17.
 */

public class Servicio {

    private String name;
    private boolean status;
    private Drawable drawable;


    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
