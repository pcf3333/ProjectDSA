package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String email;
    private int money;
    private List<Objeto> objects = new ArrayList<>();

    public Usuario(){}

    public Usuario(String nombre, String password, String email){
        this.setUsername(nombre);
        this.setEmail(email);
        this.setPassword(password);
        this.money=300;
    }

    public Usuario(String nombre, String password, int money, String email,List<Objeto> o){
        this.setPassword(password);
        this.setUsername(nombre);
        this.setEmail(email);
        this.setObjects(o);
        this.setMoney(money);
    }


    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Objeto> getObjects() {
        return objects;
    }

    public void setObjects(List<Objeto> objects) {
        this.objects = objects;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
