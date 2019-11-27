package edu.upc.dsa.models;

public class Objeto {
    private String objeto;
    private String propiedades;

    public Objeto(){}
    public Objeto(String objeto, String propiedades){
        setObjeto(objeto);
        setPropiedades(propiedades);
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
}
