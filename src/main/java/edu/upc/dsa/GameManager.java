package edu.upc.dsa;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface GameManager {
    public List<Usuario> listAlpha();
    public void addUser(Usuario u);
    public Usuario modifyUser(String id,String nombre,String apellidos,List<Objeto> objetos);
    public int numberUsers();
    public Usuario infoUser(String id);
    public int addObject(Objeto o,String id);
    public List<Objeto> getListObjects(String id);
    public void clear();
}
