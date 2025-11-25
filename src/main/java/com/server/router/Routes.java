package main.java.com.server.router;

import main.java.com.server.controller.EchoController;
import main.java.com.server.controller.GreetingController;
import main.java.com.server.controller.NotFoundController;
import main.java.com.server.controller.RequestHandler;
import main.java.com.server.http.Request;

import java.util.Map;
import java.util.Optional;

public class Routes {
    public static final Map<String, RequestHandler> mapping;

    static {
        mapping = Map.of(
                "/greeting", new GreetingController(),
                "/message", new EchoController()
        );
    }

    public static String getOrNotFound(Request request) {
        var requestHandler = mapping.get(request.getPath());
        var controller = getController(requestHandler);
        return controller.handle(request);
    }

    private static RequestHandler getController(RequestHandler requestHandler) {
        return Optional.ofNullable(requestHandler)
                .orElseGet(NotFoundController::new);
    }
}
