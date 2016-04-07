package my.playground.rest302;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/")
public interface ForwardingResourceProxyWithResponse {

    @GET
    @Path("redirect/{location}")
    public Response redirectGet(@PathParam("location") String location,
            @QueryParam("code") @DefaultValue("302") int code);

    @POST
    @Path("redirect/{location}")
    public Response redirectPost(@PathParam("location") String location,
            @QueryParam("code") @DefaultValue("302") int code);

    @GET
    @Path("static")
    public Response staticContentGet();

    @POST
    @Path("static")
    public Response staticContentPost();

}
