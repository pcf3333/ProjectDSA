package edu.upc.dsa.models;

public class Mapa {
    private int name;
    private String data;

    public Mapa(){}
    public Mapa(int nombre, String data){
        setData(data);
        setName(nombre);
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
