/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarsaleDTO;
import facades.CarsaleFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Mathias
 */
@Path("carsales")
public class CarsaleResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CarsaleFacade FACADE =  CarsaleFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarsaleResource
     */
    public CarsaleResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CarsaleResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCarsales() {
        List<CarsaleDTO> list = FACADE.getAllCarsales();  
        return GSON.toJson(list);
    }
    @Path("test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "{\"test\":\"testvalue\"}";
    }
}
