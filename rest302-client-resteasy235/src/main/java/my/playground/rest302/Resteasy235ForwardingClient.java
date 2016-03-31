package my.playground.rest302;

import org.jboss.resteasy.client.ProxyFactory;

public class Resteasy235ForwardingClient {

    public static void main(String[] args) {
        // Make sure the server is running at localhost:8080!

        ForwardingResourceProxy proxy = ProxyFactory.create(ForwardingResourceProxy.class, "http://localhost:8080");

        System.out.println("Result: " + proxy.redirectPost("static", 302));

        // System.out.println("Result: " + proxy.redirectPost("static", 307));

    }

}
