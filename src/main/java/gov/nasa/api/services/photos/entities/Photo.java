package gov.nasa.api.services.photos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Photo {
    private long id;
    private long sol;
    @JsonProperty("camera")
    private RoverCamera roverCamera;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    private Rover rover;
}