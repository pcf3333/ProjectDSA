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
        Usuario juan=new Usuario("Juan","Perez","mail@mail.com");
        Usuario andrea=new Usuario("Andrea","Sanchez","mail@mail.com");
        Usuario pere=new Usuario("Pere","Coll","mail@mail.com");
        listUsuarios=Map.of("Juan",juan,"Andrea",andrea,"Pere",pere);

    }

    @After
    public void tearDown() {
        GameManagerImpl.getInstance().clear();
    }

    @Test
    public void testAddUser() {
        //Funcion de anotar una comanda
        Usuario u=new Usuario("Juanjo","Medina","mail@mail.com");
        gameManager.addUser(u);
        assertEquals(1, gameManager.numberUsers());

    }

    @Test
    public void testOrdenarAlfa() {
        Usuario u=new Usuario("Juanjo","123456","mail@mail.com");
        gameManager.addUser(u);
        Usuario u1=new Usuario("Andrea","123456","mail@mail.com");
        gameManager.addUser(u1);
        assertEquals(gameManager.listAlpha().get(0).getUsername(),u1.getUsername());

    }
    @Test
    public void testAddObject() {
        //Funcion de anotar una comanda
        Usuario u=new Usuario("Juanjo","123456","mail@mail.com");
        gameManager.addUser(u);
        Objeto o=new Objeto("Espada","Corta mucho","https://oli.com",0,0,100,10,0);
        gameManager.addObject(o,u.getUsername());
        assertEquals(1, gameManager.getListObjects(u.getUsername()).size());

    }
}