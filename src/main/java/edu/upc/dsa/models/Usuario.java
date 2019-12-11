package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.util.RandomUtils;

public class Usuario {
    private String nombre;
    private String password;
    private String email;
    private List<Objeto> listaObjetos = new ArrayList<>();

    public Usuario(){}

    public Usuario(String nombre, String password, String email){
        this.setNombre(nombre);
        this.setEmail(email);
        this.setPassword(password);
    }

//    public Usuario(String nombre,String email,List<Objeto> o){
//        this.setId(RandomUtils.getId());
//        this.setNombre(nombre);
//        this.setEmail(email);
//        this.setListaObjetos(o);
//    }


    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
