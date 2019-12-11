package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface GameManager {
    public List<Usuario> listAlpha();
    public void addUser(Usuario u);
    public Usuario modifyUser(String nombre, String email, List<Objeto> objetos);
    public int numberUsers();
    public Usuario infoUser(String id);
    public int addObject(Objeto o,String id);
    public List<Objeto> getListObjects(String id);
    public void clear();
}
