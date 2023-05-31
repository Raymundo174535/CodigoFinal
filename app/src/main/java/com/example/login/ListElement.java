package com.example.login;

import java.io.Serializable;

public class ListElement implements Serializable
{
    public String nombre;
    public String animal;
    public String estado;
    public String fecha;
    public String codigoPostal;


    public ListElement(String nombre, String animal, String estado, String fecha, String codigoPostal) {
        this.nombre = nombre;
        this.animal = animal;
        this.estado = estado;
        this.fecha = fecha;
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
