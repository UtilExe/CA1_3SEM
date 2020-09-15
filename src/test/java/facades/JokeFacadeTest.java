package facades;

import DTO.JokeDTO;
import entities.Joke;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    private Joke joke1, joke2, joke3, joke4;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = JokeFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        joke1 = new Joke("Hvorfor smed drengen uret ud af vinduet? Fordi han ønskede at se tiden flyve af sted.", "Ingen", "Bogstavelig", "Lokumsbogen");
        joke2 = new Joke("Don't use \"beef stew\" as a computer password. It's not stroganoff.", "Mad", "Computer jokes", "rd.com");
        joke3 = new Joke("Hvorfor har blondiner vat i ørerne, når de er i svømmehallen? – Fordi de er bange for at blive hjernevasket", "Dumhed", "Blondine jokes", "de-sjove-jokes.dk");
        joke4 = new Joke("Hvordan får du glimtet frem i en blondines øjne? – Lyser med din lommelygte ind i hendes øre", "Dumhed", "Blondine jokes", "de-sjove-jokes.dk");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER TABLE JOKE AUTO_INCREMENT = 1").executeUpdate();
            em.persist(joke1);
            em.persist(joke2);
            em.persist(joke3);
            em.persist(joke4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testGetJokeCount() {
        assertEquals(facade.getJokeCount(), 4, "Expects four rows in the database");
    }

    @Test
    public void testGetAllJokes() {
        List<JokeDTO> result = facade.getAllJokes();
        assertThat(result, hasSize(4));
        
    }
    
    @Test
    public void testGetRandomJoke() {
        JokeDTO result = facade.getRandomJoke();
        
        assertNotNull(result);
    }
    
    @Test
    public void testGetJokeByID() {
        JokeDTO expected = new JokeDTO(joke2);
        JokeDTO result = facade.getJokeByID(joke2.getId());
        
        assertEquals(expected.getJoke(), result.getJoke());
    }
    
}
