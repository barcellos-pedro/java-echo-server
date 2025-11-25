package main.java.com.server.controller;

import main.java.com.server.http.Request;

public interface RequestHandler {
    String handle(Request request);
}
