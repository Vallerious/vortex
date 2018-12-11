package test;

import com.webserver.objects.Request;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RequestTest {

    @Test
    public void testBodyDecoding() throws UnsupportedEncodingException {
        // Arrange
        Request request = new Request();
        Map<String, String> formData = new HashMap<>();

        formData.put("email", "%56%41%4C%45%52%49");

        request.setFormData(formData);

        // Act
        String email = request.body("email");

        // Assert
        Assert.assertEquals(email, "VALERI");
    }

    @Test
    public void testBodyDecodingWithPlainString() throws UnsupportedEncodingException {
        // Arrange
        Request request = new Request();
        Map<String, String> formData = new HashMap<>();

        formData.put("email", "TEST");

        request.setFormData(formData);

        // Act
        String email = request.body("email");

        // Assert
        Assert.assertEquals(email, "TEST");
    }
}
