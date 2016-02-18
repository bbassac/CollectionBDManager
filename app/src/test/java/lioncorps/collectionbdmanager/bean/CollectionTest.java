package lioncorps.collectionbdmanager.bean;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by b.bassac on 17/02/2016.
 */
public class CollectionTest {

    @Test
    public void testSetBDFromSerieAsPossede() throws Exception {

        Collection c = new Collection();
        c.setId(1L);
        Serie s1 = new Serie(2L,"Arawn",false);
        Bd bd1 = new Bd(3L,"1", "Bran le Maudit", "");
        Bd bd2 = new Bd(4L,"2", "Les liens du sang", "");
        s1.getListManquante().add(bd1);
        s1.getListPossede().add(bd2);
        c.getListeSerie().add(s1);

        Serie s2 = new Serie(5L,"Serie 2",false);
        Bd bd3 = new Bd(6L,"3", "BD 3", "");
        Bd bd4 = new Bd(7L,"4", "BD 4", "");
        s2.getListManquante().add(bd3);
        s2.getListPossede().add(bd4);
        c.getListeSerie().add(s2);

        c.setBDFromSerieAsPossede(5L,6l);

        assertEquals(c.getListeSerie().size(),2);

    }
}