package gov.nasa.api.services.photos;

import gov.nasa.api.services.photos.response.MarsPhotosDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarsRoverPhotosClient {

    @GET("/mars-photos/api/v1/rovers/{rover}/photos")
    Call<MarsPhotosDTO> getPhotos(@Path("rover") String rover,
                                  @Query("sol") Integer sol,
                                  @Query("earth_date") String earthDate,
                                  @Query("camera") String camera,
                                  @Query("api_key") String apiKey,
                                  @Query("page") Integer page);
}