package edu.upc.dsa.models;

public class Objeto {
    private String objeto;
    private String propiedades;
    private int vida;
    private int ataque;
    private int escudo;
    private int speed;

    public Objeto(){}
    public Objeto(String objeto, String propiedades, int vida, int ataque, int escudo, int speed){
        setObjeto(objeto);
        setPropiedades(propiedades);
        setVida(vida);
        setAtaque(ataque);
        setEscudo(escudo);
        setSpeed(speed);
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(String propiedades) {
        this.propiedades = propiedades;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public int getEscudo() {
        return escudo;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getSpeed() {
        return speed;
    }
}
