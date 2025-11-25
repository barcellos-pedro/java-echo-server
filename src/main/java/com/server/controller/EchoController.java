package main.java.com.server.controller;

import main.java.com.server.http.HttpMethod;
import main.java.com.server.http.Request;
import main.java.com.server.http.Response;

public class EchoController implements RequestHandler {
    @Override
    public String handle(Request request) {
        if (valid(request)) {
            return Response.echo(request, Response.Type.JSON);
        }

        return Response.of("Invalid main.java.com.server.http.Request. Send a POST with a Body.", Response.Type.JSON);
    }

    private static boolean valid(Request request) {
        return request.getMethod().equals(HttpMethod.POST) && request.hasBody();
    }
}
