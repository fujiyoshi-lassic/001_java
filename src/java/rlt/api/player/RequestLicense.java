/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rlt.api.player;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author fujiyohi
 */
public class RequestLicense {

    private String id;

    /**
     * Creates a new instance of RequestLicense
     */
    private RequestLicense(String id) {
        this.id = id;
    }

    /**
     * Get instance of the RequestLicense
     */
    public static RequestLicense getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of RequestLicense class.
        return new RequestLicense(id);
    }

    /**
     * Retrieves representation of an instance of rlt.api.player.RequestLicense
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RequestLicense
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    /**
     * DELETE method for resource RequestLicense
     */
    @DELETE
    public void delete() {
    }
}
