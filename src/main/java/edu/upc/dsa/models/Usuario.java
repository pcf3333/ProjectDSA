package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.util.RandomUtils;

public class Usuario {
    private String id;
    private String nombre;
    private String apellidos;
    private List<Objeto> listaObjetos = new ArrayList<>();

    public Usuario(){}
    public Usuario(String nombre,String apellidos){
        this.setId(RandomUtils.getId());
        this.setNombre(nombre);
        this.setApellidos(apellidos);
    }
    public Usuario(String nombre,String apellidos,List<Objeto> o){
        this.setId(RandomUtils.getId());
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setListaObjetos(o);
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(List<Objeto> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
