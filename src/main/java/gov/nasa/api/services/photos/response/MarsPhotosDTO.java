package gov.nasa.api.services.photos.response;

import lombok.Data;

import java.util.List;

@Data
public class MarsPhotosDTO {
    private List<PhotoDTO> photos;
}