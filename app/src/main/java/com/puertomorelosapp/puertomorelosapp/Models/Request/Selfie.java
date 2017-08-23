package com.puertomorelosapp.puertomorelosapp.Models.Request;

import com.puertomorelosapp.puertomorelosapp.Models.Response.Photographer;

/**
 * Created by rudielavilaperaza on 8/16/17.
 */

public class Selfie {

    private String id;
    private String categoria;
    private String comentario;
    private String fecha;
    private Double height;
    private String idioma;
    private String idphotographer;
    private String itemKey;
    private String lugar;
    private String nombreEntidad;
    private String selfieOriginal;
    private String selfieThumb;
    private String subcategoria;
    private Double timeStamp;
    private Double width;

    private Photographer photographer;


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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdphotographer() {
        return idphotographer;
    }

    public void setIdphotographer(String idphotographer) {
        this.idphotographer = idphotographer;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getSelfieOriginal() {
        return selfieOriginal;
    }

    public void setSelfieOriginal(String selfieOriginal) {
        this.selfieOriginal = selfieOriginal;
    }

    public String getSelfieThumb() {
        return selfieThumb;
    }

    public void setSelfieThumb(String selfieThumb) {
        this.selfieThumb = selfieThumb;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Double timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }
}
