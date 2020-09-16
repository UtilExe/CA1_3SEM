package facades;

import DTO.CarDTO;
import entities.Car;
import java.util.Date;
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
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;
    private Car car1, car2, car3, car4;

    public CarFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = CarFacade.getFacadeExample(emf);
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
        car1 = new Car("1997", "Ford", "E350",3000, new Date(), "Daniel");
        car2 = new Car("1999", "Chevy", "Venture", 4900, new Date(), "Emil");
        car3 = new Car("2000", "Chevy", "Venture", 5000, new Date(), "Jimmy");
        car4 = new Car("1996", "Jeep", "Grand Cherokee", 4799, new Date(), "Jannich");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER TABLE CAR AUTO_INCREMENT = 1").executeUpdate();
            em.persist(car1);
            em.persist(car2);
            em.persist(car3);
            em.persist(car4);
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
    public void testGetAllCars() {
        List<CarDTO> result = facade.getAllCars();
        assertThat(result, hasSize(4));
        
    }
}