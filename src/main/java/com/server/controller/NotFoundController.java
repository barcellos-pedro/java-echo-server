package main.java.com.server.controller;

import main.java.com.server.http.Request;
import main.java.com.server.http.Response;

public class NotFoundController implements RequestHandler {
    @Override
    public String handle(Request request) {
        return Response.of("No static resource found for " + request.getPath() + ".");
    }
}
