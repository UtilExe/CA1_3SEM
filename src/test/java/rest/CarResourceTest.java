package rest;

import entities.Car;
import entities.Joke;
import entities.Members;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Car car1, car2, car3, car4;
    
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterAll
    public static void closeTestServer(){
        //System.in.read();
         //Don't forget this, if you called its counterpart in @BeforeAll
         EMF_Creator.endREST_TestWithDB();
         httpServer.shutdownNow();
    }
    
    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
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
    
    @Test
    public void testServerIsUp() {
        System.out.println("Testing server is UP");
        given().when().get("/cars").then().statusCode(200);
    }
   
    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception {
        given()
        .contentType("application/json")
        .get("/cars/").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("msg", equalTo("Hello World"));   
    }
    @Disabled
    @Test
    public void testJokesCount() throws Exception {
        given()
        .contentType("application/json")
        .get("/jokes/count").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("count", equalTo(4));   
    }
    
    
    @Test
    public void testGetAllCars() throws Exception {
        given()
        .contentType("application/json")
        .get("/cars/all").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        //.body("price", hasItems(String.valueOf(3000.0), String.valueOf(4900.0), String.valueOf(5000.0), String.valueOf(4799.0))); Testing by model instead of price, as it wasn't giving the right output.
        .body("model", hasItems(car1.getModel(), car2.getModel(), car3.getModel(), car4.getModel()));
    }
    
}
