import module java.base;

public class Parser {
    public static HashMap<String, String> headers(BufferedReader reader) throws IOException {
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

    public static char[] parseBody(Map<String, String> headers, BufferedReader reader) throws IOException {
        var contentLength = Integer.parseInt(headers.get("Content-Length"));
        var body = new char[contentLength];
        int bytesRead = reader.read(body, 0, contentLength);
        IO.println("Body bytes parsed: " + bytesRead);
        return body;
    }

    public static boolean hasBody(HashMap<String, String> headers) {
        return headers.containsKey("Content-Length");
    }

    /// - InputStream -> byte[]
    /// - InputStreamReader -> char[]
    /// - BufferedReader -> string for each line
    public static BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
