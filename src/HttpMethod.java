public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private final String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public static HttpMethod of(String value) {
        return switch (value) {
            case "GET" -> GET;
            case "POST" -> POST;
            case "PUT" -> PUT;
            case "PATCH" -> PATCH;
            case "DELETE" -> DELETE;
            default -> throw new IllegalArgumentException("Unknow method: " + value);
        };
    }

    @Override
    public String toString() {
        return value;
    }
}
