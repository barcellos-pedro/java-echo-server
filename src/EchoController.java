import java.io.IOException;

public class EchoController implements RequestHandler {
    @Override
    public String handle(Request request)  {
        if (!request.getMethod().equals(HttpMethod.POST)) {
            return Response.of("""
                    { "message": "Request method must be a POST." }""", Response.Type.JSON);
        }

        if (!request.hasBody()) {
            return Response.of("""
                    { "message": "Missing body in request." }""", Response.Type.JSON);
        }

        return Response.echo(request, Response.Type.JSON);
    }
}
