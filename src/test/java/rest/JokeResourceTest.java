package rest;

import entities.Joke;
import entities.Members;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
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
public class JokeResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Joke joke1, joke2, joke3, joke4;
    
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
    
    @Test
    public void testServerIsUp() {
        System.out.println("Testing server is UP");
        given().when().get("/jokes").then().statusCode(200);
    }
   
    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception {
        given()
        .contentType("application/json")
        .get("/jokes/").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("msg", equalTo("Hello World"));   
    }
    
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
    public void testGetAllJokes() throws Exception {
        given()
        .contentType("application/json")
        .get("/jokes/all").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("type", hasItems("Bogstavelig", "Computer jokes", "Blondine jokes", "Blondine jokes"));
    }
    
    @Disabled
    @Test
    public void testGetRandomJoke() throws Exception {
        given()
        .contentType("application/json")
        .get("/jokes/random").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("size()", is(4));
    }
    
}
