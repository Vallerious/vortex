package test;

import com.webserver.constants.WebConstants;
import com.webserver.enums.StatusCodes;
import com.webserver.objects.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseTest {
    private Response response;

    @Before
    public void beforeEveryTest() {
        this.response = new Response();
        this.response.setStatusCode(StatusCodes.OK);
        this.response.addHeader("Content-Type", "application/json");
    }

    @Test
    public void testResponseToString() {
        String textFromResponse = this.response.toString();

        StringBuilder sb = new StringBuilder();

        sb.append(WebConstants.HTTP_VERSION)
                .append(" ")
                .append(StatusCodes.OK.asStatusNumber())
                .append(" ")
                .append(StatusCodes.OK.toString())
                .append("\r\n")
                .append("Content-Type: application/json\r\n")
                .append("\r\n");

        Assert.assertEquals("Response as text: ", sb.toString(), textFromResponse);
    }
}