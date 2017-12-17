package gov.nasa.api.core.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.nasa.api.core.exceptions.TestDataException;

import java.io.IOException;
import java.net.URL;

public class JsonMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode readToJsonNode(final URL url) {
        try {
            return mapper.readTree(url);
        } catch (IOException e) {
            throw new TestDataException("Failed to read url to JsonNode: %s", url, e);
        }
    }

    public static <T> T readToObject(final JsonNode jsonNode, final Class<T> valueType) {
        String jsonNodeContent = jsonNode.toString();
        try {
            return mapper.readValue(jsonNodeContent, valueType);
        } catch (IOException e) {
            throw new TestDataException("Failed to read JsonNode content to %s object: %s", valueType.getName(),
                    jsonNodeContent, e);
        }
    }
}