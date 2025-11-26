package main.java.com.server.http;

import module java.base;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public static HttpServer create(int port) {
        return new HttpServer(port);
    }

    public void listen(Consumer<Socket> handler) throws IOException {
        IO.println("Server running at http://localhost:" + port);

        try (var server = new ServerSocket(port); var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (; ; ) {
                var socket = server.accept();
                executor.submit(() -> process(handler, socket));
            }
        }
    }

    private static void process(Consumer<Socket> handler, Socket socket) {
        try (socket) {
            IO.println(Thread.currentThread());
            handler.accept(socket);
        } catch (IOException exception) {
            IO.println("[SOCKET:ERROR] " + exception);
        }
    }
}
