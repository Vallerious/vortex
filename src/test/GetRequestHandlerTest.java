package test;

import com.webserver.enums.Method;
import com.webserver.handlers.GetRequestHandler;
import com.webserver.objects.Request;
import com.webserver.objects.Response;
import com.webserver.parsers.ResponseSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class GetRequestHandlerTest {
    private GetRequestHandler getRequestHandler;
    private Request request;
    private Response response;

    @Before
    public void beforeEveryTest() {
        this.getRequestHandler = new GetRequestHandler(new ResponseSerializer());
        this.request = new Request(Method.GET, "/", new HashMap<>(), "");
        this.response = new Response();
    }

    @Test
    public void testGetRequestWithNonExistingPage() {
        byte[] response = this.getRequestHandler.handle(
                new Request(Method.GET, "/test", new HashMap<>(), "")
        );

        String textOfResponse = new String(response);

        Assert.assertTrue(textOfResponse.contains("404"));
    }
}
