package gov.nasa.api.services.photos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoverCameraDTO {
    private int id;
    private String name;
    @JsonProperty("rover_id")
    private int roverId;
    @JsonProperty("full_name")
    private String fullName;
}