package com.puertomorelosapp.puertomorelosapp.Models.Response;

/**
 * Created by rudielavilaperaza on 18/10/17.
 */

public class ProfileInfo {

    private String name;
    private String image;
    private int likes;
    private int comments;
    private int selfies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getSelfies() {
        return selfies;
    }

    public void setSelfies(int selfies) {
        this.selfies = selfies;
    }
}
