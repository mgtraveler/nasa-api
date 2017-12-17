package gov.nasa.api.services.photos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoverDTO {
    private long id;
    private String name;
    @JsonProperty("landing_date")
    private String landingDate;
    @JsonProperty("launch_date")
    private String launchDate;
    private String status;
    @JsonProperty("max_sol")
    private long maxSol;
    @JsonProperty("max_date")
    private String maxDate;
    @JsonProperty("total_photos")
    private int totalPhotos;
    private List<CameraDTO> cameras;
}