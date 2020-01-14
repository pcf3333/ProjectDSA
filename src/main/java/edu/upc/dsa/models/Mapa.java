package edu.upc.dsa.models;

public class Mapa {
    private String name;
    private String data;

    public Mapa(){}
    public Mapa(String nombre, String data){
        setData(data);
        setName(nombre);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
