package my.playground.rest302;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/")
public interface ForwardingResourceProxy {

    @GET
    @Path("redirect/{location}")
    public String redirectGet(@PathParam("location") String location,
            @QueryParam("code") @DefaultValue("302") int code);

    @POST
    @Path("redirect/{location}")
    public String redirectPost(@PathParam("location") String location,
            @QueryParam("code") @DefaultValue("302") int code);

    @GET
    @Path("static")
    public String staticContentGet();

    @POST
    @Path("static")
    public String staticContentPost();

}
