package com.puertomorelosapp.puertomorelosapp.Models;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Categorie {

    private String Name;

    private String Image;

    private String categoria;

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
