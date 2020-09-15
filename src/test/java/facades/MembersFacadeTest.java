package facades;

import MembersDTO.MembersDTO;
import utils.EMF_Creator;
import entities.Members;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MembersFacadeTest {

    private static EntityManagerFactory emf;
    private static MembersFacade facade;
    private Members jannich, emil, daniel, jimmy;

    public MembersFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MembersFacade.getFacadeExample(emf);
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
        jannich = new Members("cph-jm312", "Jannich Højmose Møller", "South Park", 23656270);
        emil = new Members("cph-eg60", "Emil Andreas Grønlund", "Blind Spot", 29864519);
        daniel = new Members("cph-db125", "Daniel Bengtsen", "Family Guy", 41600352);
        jimmy = new Members("cph-jp327", "Jimmy Pham", "Prison Break", 61652893);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.persist(jannich);
            em.persist(emil);
            em.persist(daniel);
            em.persist(jimmy);
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
    public void testAFacadeMethod() {
        assertEquals(4, facade.getMembersCount(), "Expects two rows in the database");
    }
    
    //Jannich
    @Test
    public void testGetAllMembers() {
        List<MembersDTO> result = facade.getAllMembers();
        assertThat(result, hasSize(4));
    }

}
