package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    private Joke j1;
    private Joke j2;
    private Joke j3;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        j1 = new Joke("What did the Skeleton mobster say to my dog?", "I've got a bone to pick with you! *laugh track* ");
        j2 = new Joke("Why did the chicken cross the road?", "PLEASE OH GOD TELL ME WHY!!! *laugh track* ");
        j3 = new Joke("What did the Skeleton math teacher say to me?", "Today we will be working with ankles! *laugh track* ");

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllJokes method, of class JokeFacade.
     */
    @Test
    public void testGetAllJokes() {
        List<JokeDTO> jokes = facade.getAllJokes();
        assertTrue(jokes != null);
        assertThat(jokes, everyItem(hasProperty("punch")));
    }
    
    /**
     * Test of getJokeCount method, of class JokeFacade.
     */
    @Test
    public void testGetJokeCount() {
        assertEquals(3, facade.getJokeCount());
    }

    /**
     * Test of getJokeByID method, of class JokeFacade.
     */
    @Test
    public void testGetJokeByID() {
        int expResult = j1.getId();
        JokeDTO dto = facade.getJokeByID(expResult);
        int result = dto.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRandomJoke method, of class JokeFacade.
     */
    @Test
    public void testGetRandomJoke() {
        JokeDTO dto = facade.getRandomJoke();
        assertTrue(dto != null);
        
    }

}
