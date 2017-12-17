package gov.nasa.api.services.photos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhotoDTO {
    private long id;
    private long sol;
    @JsonProperty("camera")
    private RoverCameraDTO roverCamera;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    private RoverDTO rover;
}