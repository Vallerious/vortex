package test;

import com.webserver.utils.KeyValueUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class KeyValueUtilsTest {
    private Map<String, Object> keyValuePairs;

    @Before
    public void beforeEveryTest() {
        keyValuePairs = new HashMap<>();
    }

    @Test
    public void testHeaderTextFormatting() {
        this.keyValuePairs.put("a", "b");
        this.keyValuePairs.put("c", "d");

        String formattedMap = KeyValueUtils.keyValueToString(this.keyValuePairs);
        String expected = "a: b\r\nc: d\r\n";

        Assert.assertEquals(expected, formattedMap);
    }

    @Test
    public void testHeaderTextFormattingWithNoHeaders() {
        String formattedMap = KeyValueUtils.keyValueToString(this.keyValuePairs);

        Assert.assertEquals("", formattedMap);
    }

    @Test
    public void testQueryParameterParser() {
        String queryParams = "email=valericfbg%40gmail.com&password=123&nula=";

        Map<String, String> params = KeyValueUtils.parseQuerylikeParams(queryParams);

        Assert.assertEquals(params.get("email"), "valericfbg%40gmail.com");
        Assert.assertEquals(params.get("password"), "123");
        Assert.assertEquals(params.get("nula"), null);
    }
}
