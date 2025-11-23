import static java.nio.charset.StandardCharsets.UTF_8;

void main() throws IOException {
    // Create Server
    try (var server = new ServerSocket(8080)) {
        // Main Loop
        for (; ; ) {
            // Accept client connection/socket
            try (var socket = server.accept()) {
                var reader = getReader(socket);
                var rawReqLine = reader.readLine();

                var requestLine = RequestLine.of(rawReqLine);
                var headers = parseHeaders(reader);

                IO.println(requestLine);
                IO.println(headers);

                try (var out = socket.getOutputStream()) {
                    var responseData = missingBody(headers) ?
                            response("Hello World") :
                            requestBody(headers, reader);

                    out.write(responseData);
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    } catch (RuntimeException exception) {
        IO.println("[ERROR] " + exception);
    }
}

private static HashMap<String, String> parseHeaders(BufferedReader reader) throws IOException {
    var headers = new HashMap<String, String>();
    var data = "";

    while (!(data = reader.readLine()).isEmpty()) {
        var keyValue = data.split(": ", 2);

        if (keyValue.length == 2) {
            String key = keyValue[0];
            String value = keyValue[1];
            headers.put(key, value);
        }
    }

    return headers;
}

private static char[] parseBody(Map<String, String> headers, BufferedReader reader) throws IOException {
    var contentLength = getContentLength(headers);
    var body = new char[contentLength];
    int bytesRead = reader.read(body, 0, contentLength);
    IO.println("Body bytes parsed: " + bytesRead);
    return body;
}

private static Integer getContentLength(Map<String, String> headers) {
    return Integer.valueOf(headers.get("Content-Length"));
}

private static boolean missingBody(HashMap<String, String> headers) {
    return !containsBody(headers);
}

private static boolean containsBody(HashMap<String, String> headers) {
    return headers.containsKey("Content-Length");
}

public static String TEMPLATE = """
        HTTP/1.1 200 OK\r
        Content-Length: %s\r
        Content-Type: text/plain\r
        \r
        %s""";

public static byte[] requestBody(Map<String, String> headers, BufferedReader reader) throws IOException {
    var reqBody = parseBody(headers, reader);
    return response(reqBody);
}

private static byte[] response(char[] value) {
    return response(new String(value));
}

private static byte[] response(String value) {
    byte[] bodyBytes = value.getBytes(UTF_8);
    var response = TEMPLATE.formatted(bodyBytes.length, value);
    return response.getBytes(UTF_8);
}

/// - InputStream -> byte[]
/// - InputStreamReader -> char[]
/// - BufferedReader -> string for each line
private static BufferedReader getReader(Socket socket) throws IOException {
    return new BufferedReader(new InputStreamReader(socket.getInputStream()));
}

record RequestLine(String method, String path, String protocol) {
    public static RequestLine of(String line) {
        var data = line.split(" ");
        var method = data[0];
        var path = data[1];
        var protocol = data[2];
        return new RequestLine(method, path, protocol);
    }
}