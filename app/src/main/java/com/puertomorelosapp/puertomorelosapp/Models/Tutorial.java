package com.puertomorelosapp.puertomorelosapp.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by rudielavilaperaza on 9/26/17.
 */

public class Tutorial {

    private String title;
    private String subtitle;
    private Drawable image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
