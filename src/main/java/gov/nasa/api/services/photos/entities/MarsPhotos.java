package gov.nasa.api.services.photos.entities;

import lombok.Data;

import java.util.List;

@Data
public class MarsPhotos {
    private List<Photo> photos;
}