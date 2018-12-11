package test;

import com.webserver.db.UserDB;
import com.webserver.enums.Method;
import com.webserver.enums.StatusCodes;
import com.webserver.handlers.LoginRequestHandler;
import com.webserver.handlers.RequestHandler;
import com.webserver.objects.Request;
import com.webserver.parsers.ResponseSerializer;
import com.webserver.session.ISession;
import com.webserver.session.Session;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class LoginRequestHandlerTest {
    private ISession session;
    private Request request;
    private Map<String, String> formData;

    @Before
    public void setup() {
        formData = new HashMap<>();
        this.session = new Session();
        request = new Request(Method.POST, "/login", new HashMap<>(), "some form data");
    }

    @After
    public void afterEachTest() {
        formData.clear();
    }

    @Test
    public void testLoginActionFail() {
        RequestHandler handler = new LoginRequestHandler(new ResponseSerializer(), session);
        formData.put("email", "valeri");
        formData.put("password", "1234");
        request.setFormData(formData);
        byte[] response = handler.handle(request);
        String responseAsText = new String(response);

        Assert.assertTrue(responseAsText.contains("403"));
    }

    @Test
    public void testLoginActionFailWithPassword() {
        RequestHandler handler = new LoginRequestHandler(new ResponseSerializer(), session);
        formData.put("email", "Pesho");
        formData.put("password", "12345");
        request.setFormData(formData);
        byte[] response = handler.handle(request);
        String responseAsText = new String(response);

        Assert.assertTrue(responseAsText.contains("403"));
    }


    @Test
    public void testLoginActionSuccess() {
        RequestHandler handler = new LoginRequestHandler(new ResponseSerializer(), session);
        formData.put("email", "Pesho");
        formData.put("password", "1234");
        request.setFormData(formData);
        byte[] response = handler.handle(request);
        String responseAsText = new String(response);

        Assert.assertTrue(responseAsText.contains("" + StatusCodes.Found.asStatusNumber()));
        Assert.assertTrue(responseAsText.contains("1c"));
        Assert.assertTrue(responseAsText.contains("Set-Cookie: authToken=1c"));
    }

}
