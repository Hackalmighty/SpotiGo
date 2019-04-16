package com.example.tecsup.spotigo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class PlayLista implements Serializable {
    private String nombre;
    static private List<Musica> Lista = new ArrayList<Musica>();


    public PlayLista( String nombre) {

        this.nombre = nombre;
    }

    public List<Musica> getLista() {
        return Lista;
    }

    public void setLista(List<Musica> lista) {
        Lista = lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
