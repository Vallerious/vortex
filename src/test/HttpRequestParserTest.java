package test;

import com.webserver.enums.Method;
import com.webserver.objects.Request;
import com.webserver.parsers.HttpRequestParser;
import org.junit.Assert;
import org.junit.Test;

public class HttpRequestParserTest {
    @Test
    public void parseTextAsResponse() {
        String text = this.getRequestText(Method.GET);

        HttpRequestParser hrq = new HttpRequestParser();

        Request req = hrq.parse(text);

        Assert.assertEquals(req.getMethod(), Method.GET);
        Assert.assertEquals(req.getUrl(), "/hello.htm");
        Assert.assertEquals(req.getHeader("Connection"), "Keep-Alive");
        Assert.assertEquals(req.getHeaders().size(), 5);
    }

    private String getRequestText(Method method) {
        return method + " /hello.htm HTTP/1.1\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n" +
                "Host: www.tutorialspoint.com\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Connection: Keep-Alive\r\n";
    }
}
