import java.util.Objects;

public record Request(String method, String path, String protocol) {
    public Request {
        Objects.requireNonNull(method, "Method cannot be null");
        Objects.requireNonNull(path, "Path cannot be null");
        Objects.requireNonNull(protocol, "Protocol cannot be null");
    }

    public static Request of(String line) {
        var data = line.split(" ", 3);
        return new Request(data[0], data[1], data[2]);
    }
}