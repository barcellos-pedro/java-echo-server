public class EchoController implements RequestHandler {
    @Override
    public String handle(Request request) {
        if (valid(request)) {
            return Response.echo(request, Response.Type.JSON);
        }

        return Response.of("Invalid Request. Send a POST with a Body.", Response.Type.JSON);
    }

    private static boolean valid(Request request) {
        return request.getMethod().equals(HttpMethod.POST) && request.hasBody();
    }
}
