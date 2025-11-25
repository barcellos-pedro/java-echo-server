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
