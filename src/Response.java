import module java.base;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Response {
    public static byte[] getBody(String value) {
        return getBody(value, Type.DEFAULT);
    }

    public static byte[] getBody(String value, Type type) {
        var responseBody = bindValues(type, value);
        return responseBody.getBytes(UTF_8);
    }

    public static byte[] getBody(Map<String, String> headers, BufferedReader reader, Type type) throws IOException {
        var reqBody = Parser.parseBody(headers, reader);
        return getBody(reqBody, type);
    }

    public static byte[] getBody(char[] value, Type type) {
        var responseBody = bindValues(type, new String(value));
        return responseBody.getBytes(UTF_8);
    }

    public static String bindValues(Type type, String data) {
        var contentLength = data.getBytes(UTF_8).length;

        return switch (type) {
            case DEFAULT -> ResponseTemplate.TEXT.formatted(contentLength, data);
            case JSON -> ResponseTemplate.JSON.formatted(contentLength, data);
        };
    }

    public enum Type {
        DEFAULT, JSON
    }
}
