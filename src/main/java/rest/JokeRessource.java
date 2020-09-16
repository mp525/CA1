package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JokeDTO;
import utils.EMF_Creator;
import facades.JokeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Vibeke and Nikolaj
 */
@Path("joke")
public class JokeRessource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();



    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getJokeCount();
        return "{\"count\":" + count + "}"; // Manual, no DTO in this one
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        List<JokeDTO> list = FACADE.getAllJokes();
        return GSON.toJson(list);
    }
    
    @Path("/id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJById(@PathParam("id") int id){
        JokeDTO Joke = FACADE.getJokeByID(id);
        
        return GSON.toJson(Joke);
    }
    
    @Path("/random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJoke(){
        JokeDTO Joke = FACADE.getRandomJoke();
        return GSON.toJson(Joke);
    }

    
}
