package gov.nasa.api.services.photos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhotoDTO {
    private int id;
    private int sol;
    @JsonProperty("camera")
    private RoverCameraDTO roverCamera;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    private RoverDTO rover;
}