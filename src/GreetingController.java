public class GreetingController implements RequestHandler {
    @Override
    public String handle(Request request) {
        return Response.of("Hello World!");
    }
}
