package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import org.junit.Test;

public class SessionTest {

    @Test
    public void addUser() {
        Session session = FactorySession.openSession();
        session.save(new Usuario("Toni", "kskks","erftyhujik,l"));
        session.close();
    }
}
