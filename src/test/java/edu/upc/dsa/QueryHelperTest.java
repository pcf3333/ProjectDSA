package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.*;
import org.junit.Assert;
import org.junit.Test;

public class QueryHelperTest {


    @Test
    public void testQueryINSERT() {
        Assert.assertEquals("INSERT INTO Employee (ID, name, surname, salary) VALUES (?, ?, ?, ?)",
                QueryHelper.createQueryINSERT(new Usuario("Juan", "lopez","btyuhn"),"users"));
    }

    @Test
    public void testQuerySELECT() {
        Assert.assertEquals("SELECT * FROM Employee WHERE ID = ?",
                QueryHelper.createQuerySELECT("users","username", "Juan","*"));
    }


}
