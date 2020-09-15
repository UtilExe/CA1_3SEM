package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.JokeFacade;
import utils.EMF_Creator;
import facades.MembersFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("jokes")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final JokeFacade facade =  JokeFacade.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllJoke() {
        return gson.toJson(facade.getAllJokes());
    }
    
    @Path("random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomJoke(){
        return gson.toJson(facade.getRandomJoke());
    }
    
    @Path("count")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokeCount() {
        long count = facade.getJokeCount();
        
        return "{\"count\":"+count+"}";
    }
    
    @Path("/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokeByID(@PathParam("id") long id) {
        return gson.toJson(facade.getJokeByID(id));
    }
    
    
    
    
}
