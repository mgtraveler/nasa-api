package gov.nasa.api;

import com.fasterxml.jackson.databind.JsonNode;
import gov.nasa.api.core.annotations.TestData;
import gov.nasa.api.core.testng.CustomTestNGListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;
import java.net.URL;

import static gov.nasa.api.core.mapper.JsonMapper.*;

@Listeners(CustomTestNGListener.class)
public class BaseTest {

    protected static final String DATA_PROVIDER_METHOD = "loadTestDataFromJson";

    @DataProvider(name = DATA_PROVIDER_METHOD)
    public Object[][] loadTestDataFromJson(Method method) {
        String jsonPath = new StringBuilder().
                append("/").
                append(method.getAnnotation(TestData.class).value()).
                toString();
        URL url = this.getClass().getResource(jsonPath);
        JsonNode jsonNode = readToJsonNode(url);
        return new Object[][]{{jsonNode}};
    }
}