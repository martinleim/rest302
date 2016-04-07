package my.playground.rest302;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class Resteasy235ForwardingClientWithResponse {

    public static void main(String[] args) {
        // Make sure the server is running at localhost:8080!

        URI baseURI = UriBuilder.fromUri("http://localhost:8080").build();

        ForwardingResourceProxyWithResponse proxy = createProxy(baseURI);

        System.out.println("Status: " + proxy.redirectGet("static", 302).getStatus());

    }

    private static ForwardingResourceProxyWithResponse createProxy(URI baseURI) {
        ResteasyProviderFactory providerFactory = ResteasyProviderFactory.getInstance();
        ForwardingResourceProxyWithResponse proxy = ProxyFactory.create(ForwardingResourceProxyWithResponse.class,
                baseURI, ClientRequest.getDefaultExecutor(), providerFactory);
        return proxy;
    }

}
