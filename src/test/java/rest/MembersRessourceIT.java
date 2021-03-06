package rest;

import entities.GroupMember;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.lang.reflect.Array;
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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MembersRessourceIT {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static GroupMember r1,r2;
    
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
        emf = EMF_Creator.createEntityManagerFactory();
        
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
        
        
        EntityManager em = emf.createEntityManager();
        r1 = new GroupMember("Matti Hansen", "cph-mh829", "Curlyfries", "The FellowShip of the Ring", "BurgerSpisning");
        r2= new GroupMember("Nikolaj Trankjaer", "cph-nt105", "Rebaked Potatoes", "Neil Breen: Twisted Pair", "Tabletop Wargaming");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from GroupMember").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            
            em.getTransaction().commit();
        } finally { 
            em.close();
        }
    }
    
    @AfterAll
    public static void closeTestServer(){
        
         EMF_Creator.endREST_TestWithDB();
         httpServer.shutdownNow();
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/groupmembers/all").then().statusCode(200);
    }
   
    @Test
    public void testApiAll() throws Exception {
        String[] a ={"Matti Hansen","Nikolaj Trankjaer"};
        given()
        .contentType("application/json")
        .get("/groupmembers/all").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("name", hasItems(a));
    }
    
    
}

