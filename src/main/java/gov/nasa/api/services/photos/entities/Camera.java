package gov.nasa.api.services.photos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Camera {
    private String name;
    @JsonProperty("full_name")
    private String fullName;
}