package facades;

import dtos.CarsaleDTO;
import entities.Carsale;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author Mathias
 */
public class CarsaleFacadeTest {

    private static EntityManagerFactory emf;
    private static CarsaleFacade facade;

    private Carsale c1;
    private Carsale c2;
    private Carsale c3;

    public CarsaleFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CarsaleFacade.getFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        c1 = new Carsale(1997, "Ford", "E350", 3000, "Hans Pilgaard");
        c2 = new Carsale(1999, "Chevy", "Venture", 4900, "James Bond");
        c3 = new Carsale(2000, "Chevy", "Venture", 5000, "Kim Larsen");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Carsale").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetCarsaleCount() {
        assertEquals(3, facade.getCarsaleCount(), "Expects three rows in the database");
    }

    /**
     * Test of getAllCarsales method, of class CarsaleFacade.
     */
    @Test
    public void testGetAllCarsales() {
        List<CarsaleDTO> movies = facade.getAllCarsales();
        assertEquals(3, facade.getCarsaleCount(), "Expects three carsales in the database");
        assertThat(movies, everyItem(hasProperty("price")));
        assertThat(movies, hasItems( // or contains or containsInAnyOrder 
                Matchers.<CarsaleDTO>hasProperty("price", is(3000)),
                Matchers.<CarsaleDTO>hasProperty("price", is(4900)),
                Matchers.<CarsaleDTO>hasProperty("price", is(5000))
        )
        );

    }

}
