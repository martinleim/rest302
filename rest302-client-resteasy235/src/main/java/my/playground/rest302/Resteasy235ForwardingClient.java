package my.playground.rest302;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class Resteasy235ForwardingClient {

    public static void main(String[] args) {
        // Make sure the server is running at localhost:8080!

        URI baseURI = UriBuilder.fromUri("http://localhost:8080").build();

        ForwardingResourceProxy proxyVarA = createProxyVariantA(baseURI);
        ForwardingResourceProxy proxyVarB = createProxyVariantB(baseURI);

        // System.out.println("Result: " + proxyVarA.redirectPost("static", 302));
        System.out.println("Result: " + proxyVarB.redirectPost("static", 302));

    }

    private static ForwardingResourceProxy createProxyVariantB(URI baseURI) {
        ResteasyProviderFactory providerFactory = ResteasyProviderFactory.getInstance();
        providerFactory.getClientExecutionInterceptorRegistry().register(new Http302AsErrorInterceptor());
        ForwardingResourceProxy proxy = ProxyFactory.create(ForwardingResourceProxy.class, baseURI,
                ClientRequest.getDefaultExecutor(), providerFactory);
        return proxy;
    }

    private static ForwardingResourceProxy createProxyVariantA(URI baseURI) {
        ClientRequestFactory factory = new ClientRequestFactory(baseURI);
        factory.getSuffixInterceptors().registerInterceptor(new Http302AsErrorInterceptor());
        ForwardingResourceProxy proxy = factory.createProxy(ForwardingResourceProxy.class);
        return proxy;
    }

}
