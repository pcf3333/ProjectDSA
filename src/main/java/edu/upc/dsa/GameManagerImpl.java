package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.*;

public class GameManagerImpl implements GameManager {
    private static GameManagerImpl instance;
    private Map<String, Usuario> usuarios;

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private GameManagerImpl(){
        this.setUsuarios(new HashMap<>());
    }
    public static GameManagerImpl getInstance(){
        if (instance ==null)
            instance =new GameManagerImpl();
        return instance;

    }


    public List<Usuario> listAlpha() {
        List<Usuario> lp=new ArrayList<>(this.getUsuarios().values());
        Collections.sort(lp, new CompararUsuarios());
        logger.info("Lista ordenada");
        return lp;
    }

    public void addUser(Usuario u) {
        getUsuarios().put(u.getUsername(), u);
        logger.info("Usuario añadido");

    }

    public Usuario modifyUser( String nombre, String email, List<Objeto> objetos) {
        getUsuarios().get(nombre).setUsername(nombre);
        getUsuarios().get(nombre).setEmail(email);
        getUsuarios().get(nombre).setObjects(objetos);
        logger.info("Usuario modificado");
        return getUsuarios().get(nombre);

    }

    public int numberUsers() {
        int a =getUsuarios().values().size();
        logger.info("El número de usuarios es "+ a);
        return a;
    }

    public Usuario infoUser(String id) {
        logger.info("Usuario obtenido");
        return getUsuarios().get(id);
    }

    public int addObject(Objeto o,String id) {
        try {

            getUsuarios().get(id).getObjects().add(o);
            logger.info("Objeto añadido");
            return 0;

        }
        catch (Exception e){
            return -1;
        }
    }

    public List<Objeto> getListObjects(String id) {
        logger.info("Lista de objetos obtenida");
        return getUsuarios().get(id).getObjects();
    }

    public void clear(){
        this.getUsuarios().clear();
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public static class CompararUsuarios implements Comparator<Usuario> {
        public int compare(Usuario pr1, Usuario pr2) {
            return (pr1.getUsername()).compareToIgnoreCase(pr2.getUsername());
        }
    }

}

