package my.playground.rest302;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class Resteasy309ForwardingClient {

    public static void main(String[] args) {
        // Make sure the server is running at localhost:8080!

        ResteasyWebTarget target =
                (ResteasyWebTarget) ResteasyClientBuilder.newClient().target("http://localhost:8080");
        ForwardingResourceProxy proxy = target.proxy(ForwardingResourceProxy.class);

        // ForwardingResourceProxy proxy = ProxyFactory.create(ForwardingResourceProxy.class, "http://localhost:8080");


        System.out.println("GET: " + proxy.redirectGet("static", 302));
        System.out.println("POST: " + proxy.redirectPost("static", 302));

    }

}
