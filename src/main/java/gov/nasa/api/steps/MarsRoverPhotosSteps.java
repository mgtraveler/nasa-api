package gov.nasa.api.steps;

import gov.nasa.api.enums.Rover;
import gov.nasa.api.services.photos.MarsPhotosParams;
import gov.nasa.api.services.photos.response.MarsPhotosDTO;
import org.apache.commons.httpclient.HttpStatus;
import retrofit2.Response;

import java.io.IOException;

import static gov.nasa.api.services.ServiceProvider.getMarsRoverPhotosClient;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverPhotosSteps {

    public MarsPhotosDTO getMarsPhotos(final Rover rover, final MarsPhotosParams marsPhotosParams) {
        try {
            Response response = getMarsRoverPhotosClient()
                    .getPhotos(rover.toString().toLowerCase(), marsPhotosParams.getSol(), marsPhotosParams.getEarthDate(),
                            marsPhotosParams.getCamera(), marsPhotosParams.getApiKey(), marsPhotosParams.getPage())
                    .execute();
            assertThat(response.code()).as("Response code").isEqualTo(HttpStatus.SC_OK);
            return (MarsPhotosDTO) response.body();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to execute get request to MarsRoverPhotosClient", e);
        }
    }
}