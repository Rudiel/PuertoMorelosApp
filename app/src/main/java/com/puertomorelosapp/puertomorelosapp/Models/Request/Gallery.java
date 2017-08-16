package com.puertomorelosapp.puertomorelosapp.Models.Request;

/**
 * Created by rudielavilaperaza on 8/16/17.
 */

public class Gallery {

    private int height;
    private String original;
    private String thumb;
    private Double timeStamp;
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Double timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
