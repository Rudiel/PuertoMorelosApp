package com.puertomorelosapp.puertomorelosapp.Models;

import android.app.Service;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public class SubCategory {

    private String id;

    private int activo;
    private int comentariosCantidad;
    private String descripcion;
    private String descripcion2;
    private String descripcion3;
    private String direccion;
    private String fechadias;
    private String horafin;
    private String horainicio;
    private String imageBackgroundContent;
    private String imageHeader;
    private String latitud;
    private String longitud;
    private int loveCantidad;
    private String nombre;
    private int prioridad;
    private String tags;
    private String telefono;
    private String titulo;
    private String titulo2;
    private String titulo3;
    private String acceso;


    private int likes;
    private int comments;

    private HashMap<String,Boolean> Servicios;

    public HashMap<String, Boolean> getServicios() {
        return Servicios;
    }

    public void setServicios(HashMap<String, Boolean> servicios) {
        Servicios = servicios;
    }

    public String getFechadias() {
        return fechadias;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public void setFechadias(String fechadias) {
        this.fechadias = fechadias;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getComentariosCantidad() {
        return comentariosCantidad;
    }

    public void setComentariosCantidad(int comentariosCantidad) {
        this.comentariosCantidad = comentariosCantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getDescripcion3() {
        return descripcion3;
    }

    public void setDescripcion3(String descripcion3) {
        this.descripcion3 = descripcion3;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImageBackgroundContent() {
        return imageBackgroundContent;
    }

    public void setImageBackgroundContent(String imageBackgroundContent) {
        this.imageBackgroundContent = imageBackgroundContent;
    }

    public String getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(String imageHeader) {
        this.imageHeader = imageHeader;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getLoveCantidad() {
        return loveCantidad;
    }

    public void setLoveCantidad(int loveCantidad) {
        this.loveCantidad = loveCantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo2() {
        return titulo2;
    }

    public void setTitulo2(String titulo2) {
        this.titulo2 = titulo2;
    }

    public String getTitulo3() {
        return titulo3;
    }

    public void setTitulo3(String titulo3) {
        this.titulo3 = titulo3;
    }
}
