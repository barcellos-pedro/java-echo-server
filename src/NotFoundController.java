public class NotFoundController implements RequestHandler {
    @Override
    public String handle(Request request) {
        return Response.of("No static resource found for " + request.getPath() + ".");
    }
}
