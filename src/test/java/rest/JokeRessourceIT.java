/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Joke;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author SJUBE
 */
public class JokeRessourceIT {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private Joke j1;
    private Joke j2;
    private Joke j3;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //First Drop and Rebuild the test database 
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        //Set System property so the project executed by the Grizly-server wil use this same database
        EMF_Creator.startREST_TestWithDB();

        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void tearDownClass() {

        httpServer.shutdownNow();
        EMF_Creator.endREST_TestWithDB();
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

    /**
     * Test of getMovieCount method, of class JokeRessource.
     */
    @Test
    public void testGetMovieCount() {
        given()
                .get("/joke/count")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(3));

    }

    /**
     * Test of getAllJokes method, of class JokeRessource.
     */
    @Test
    public void testGetAllJokes() {
        given().get("/joke/all")
                .then()
                .assertThat().statusCode(HttpStatus.OK_200.getStatusCode())
                .body("punch", hasItems("I've got a bone to pick with you! *laugh track* ", "PLEASE OH GOD TELL ME WHY!!! *laugh track* ", "Today we will be working with ankles! *laugh track* "));
    }


    
         @Test
    public void testFindById() {
         given()
         .pathParam("id",j2.getId())
         .get("joke/id/{id}")
         .then()
         .assertThat()
         .body("punch", equalTo("PLEASE OH GOD TELL ME WHY!!! *laugh track* "));
          
    }
        /**
     * Test of getJById method, of class JokeRessource.
     */
//    @Test
//    public void testGetJById() {
//        given()
//                .get("/joke/id")
//    }
//    

}
