package my.playground.rest302;

import javax.ws.rs.WebApplicationException;

import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;

public class Http302AsErrorInterceptor implements ClientExecutionInterceptor {

    @Override
    public ClientResponse<?> execute(ClientExecutionContext ctx) throws Exception {
        ClientResponse<?> response = ctx.proceed();
        if (response.getStatus() == 302) {
            throw new WebApplicationException(response);
        }
        return response;
    }

}
