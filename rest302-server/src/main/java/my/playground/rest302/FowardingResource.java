package my.playground.rest302;

import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Named
@Path("/")
public class FowardingResource {

    @GET
    @Path("redirect/{location}")
    public Response redirectGet(@PathParam("location") String location,
            @QueryParam("code") @DefaultValue("302") int code) {
        return redirectInternal(location, code);
    }

    @POST
    @Path("redirect/{location}")
    public Response redirectPost(@PathParam("location") String location,
            @QueryParam("code") @DefaultValue("302") int code) {
        return redirectInternal(location, code);
    }

    @GET
    @Path("static")
    public Response staticContentGet() {
        return staticContentInternal();
    }

    @POST
    @Path("static")
    public Response staticContentPost() {
        return staticContentInternal();
    }


    private Response redirectInternal(String location, int code) {
        System.out.println("redirectInternal");
        return Response.status(code).header("Location", location).build();
    }

    private Response staticContentInternal() {
        System.out.println("staticContentInternal");
        return Response.ok("GOT ME").build();
    }

}
