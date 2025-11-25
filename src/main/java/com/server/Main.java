import main.java.com.server.http.HttpServer;
import main.java.com.server.router.Router;

void main() {
    try {
        HttpServer.create(8080).listen(Router::handle);
    } catch (IOException exception) {
        IO.println("[SERVER:ERROR] " + exception);
    }
}
