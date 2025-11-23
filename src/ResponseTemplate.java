public class ResponseTemplate {
    public static String TEXT = """
            HTTP/1.1 200 OK\r
            Content-Length: %s\r
            Content-Type: text/plain\r
            \r
            %s""";

    public static String JSON = """
            HTTP/1.1 200 OK\r
            Content-Length: %s\r
            Content-Type: application/json\r
            \r
            %s""";
}
