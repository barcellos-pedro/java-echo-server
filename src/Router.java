import java.io.IOException;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Router {
    public static void handle(Socket socket) {
        try (var response = socket.getOutputStream()) {
            var data = Router.route(socket);
            response.write(data.getBytes(UTF_8));
        } catch (IOException exception) {
            IO.println("[REQUEST:ERROR] " + exception);
        }
    }

    public static String route(Socket socket) throws IOException {
        var request = Request.of(socket);
        IO.println(request);

        return switch (request.getPath()) {
            case "/greeting" -> new GreetingController().handle(request);
            case "/message" -> new EchoController().handle(request);
            default -> new NotFoundController().handle(request);
        };
    }
}
