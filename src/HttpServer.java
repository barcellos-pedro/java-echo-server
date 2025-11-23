import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
        IO.println("Server running at http://localhost:" + port);
    }

    public static HttpServer of(int port) {
        return new HttpServer(port);
    }

    public void start() {
        try (var server = new ServerSocket(port)) {
            for (; ; ) {
                try (var request = server.accept()) {
                    var reader = Parser.getReader(request);
                    var rawReqLine = reader.readLine();

                    var requestLine = Request.of(rawReqLine);
                    var headers = Parser.headers(reader);

                    IO.println(requestLine);
                    IO.println(headers);

                    try (var response = request.getOutputStream()) {
                        var responseData = Parser.hasBody(headers) ?
                                Response.getBody(headers, reader, Response.Type.JSON) :
                                Response.getBody("Hello World");

                        response.write(responseData);
                    }
                } catch (IOException exception) {
                    IO.println("[REQUEST:ERROR] " + exception);
                }
            }
        } catch (IOException exception) {
            IO.println("[SERVER:ERROR] " + exception);
        }
    }
}
