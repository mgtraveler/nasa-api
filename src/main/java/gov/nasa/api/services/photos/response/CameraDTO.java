package gov.nasa.api.services.photos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CameraDTO {
    private String name;
    @JsonProperty("full_name")
    private String fullName;
}