package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GameManagerImplTest {

    GameManagerImpl gameManager = null;
    Map<String, Usuario> listUsuarios = null;
    Logger logger = Logger.getLogger(GameManagerImplTest.class);


    @Before
    public void setUp(){
        gameManager = GameManagerImpl.getInstance();
        Usuario juan=new Usuario("Juan","Perez");
        Usuario andrea=new Usuario("Andrea","Sanchez");
        Usuario pere=new Usuario("Pere","Coll");
        listUsuarios=Map.of("Juan",juan,"Andrea",andrea,"Pere",pere);

    }

    @After
    public void tearDown() {
        GameManagerImpl.getInstance().clear();
    }

    @Test
    public void testAddUser() {
        //Funcion de anotar una comanda
        Usuario u=new Usuario("Juanjo","Medina");
        gameManager.addUser(u);
        assertEquals(1, gameManager.numberUsers());

    }

    @Test
    public void testOrdenarAlfa() {
        Usuario u=new Usuario("Juanjo","Medina");
        gameManager.addUser(u);
        Usuario u1=new Usuario("Andrea","Perez");
        gameManager.addUser(u1);
        assertEquals(gameManager.listAlpha().get(0).getId(),u1.getId());

    }
    @Test
    public void testAddObject() {
        //Funcion de anotar una comanda
        Usuario u=new Usuario("Juanjo","Medina");
        gameManager.addUser(u);
        Objeto o=new Objeto("Espada","Corta mucho");
        gameManager.addObject(o,u.getId());
        assertEquals(1, gameManager.getListObjects(u.getId()).size());

    }
}