/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsc;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import lsc.server.viewingClass;


/**
 * REST Web Service
 *
 * @author fujiyohi
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of lsc.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.TEXT_PLAIN)
    //@Produces({"application/xml", "application/json"})
    //@Path("{from}/{to}/jsonp")
    @Produces({"application/javascript"})
    public String getJSON(@DefaultValue("") @QueryParam("userid") String userid,
            @DefaultValue("") @QueryParam("keyid") String keyid,
            @DefaultValue("") @QueryParam("onetimekey") String onetimekey,
            @DefaultValue("") @QueryParam("drm_key_id") String drm_key_id) {
        viewingClass ving = new viewingClass();
        
        return "ok=" + userid + keyid + onetimekey + drm_key_id;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postHandler(String content) {
        return content;
    }
}
