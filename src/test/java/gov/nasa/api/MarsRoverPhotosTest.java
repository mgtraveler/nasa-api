package gov.nasa.api;

import gov.nasa.api.enums.Rover;
import gov.nasa.api.services.photos.MarsPhotosParams;
import gov.nasa.api.services.photos.response.MarsPhotosDTO;
import gov.nasa.api.services.photos.response.PhotoDTO;
import gov.nasa.api.steps.MarsRoverPhotosSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static gov.nasa.api.core.common.ErrorMsg.forNoMatch;
import static gov.nasa.api.core.logger.Logger.LOGGER;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverPhotosTest {

    private static final Rover curiosityRover = Rover.CURIOSITY;

    private MarsRoverPhotosSteps marsRoverPhotosSteps = new MarsRoverPhotosSteps();

    @DataProvider
    public Object[][] photosBySolAndEarthDate() {
        return new Object[][]{
                {10, 1000, "2015-05-30"}
        };
    }

    @Test(description = "Mars photos made by 'Curiosity' rover are equal based on sol and earth date",
            dataProvider = "photosBySolAndEarthDate")
    public void verifyMarsCuriosityPhotosBySolAndEarthDateAreEqual(final int photosQty, final long sol, final String earthDate) {
        //given
        MarsPhotosParams paramsBySol = new MarsPhotosParams().withSol(sol);
        MarsPhotosParams paramsByEarthDate = new MarsPhotosParams().withEarthDate(earthDate);

        // when
        MarsPhotosDTO marsPhotosBySol = marsRoverPhotosSteps.getMarsPhotos(curiosityRover, paramsBySol);
        MarsPhotosDTO marsPhotosByEarthDate = marsRoverPhotosSteps.getMarsPhotos(curiosityRover, paramsByEarthDate);
        Map<Long, PhotoDTO> comparedPhotosBySol = marsPhotosBySol.getPhotos()
                .stream()
                .limit(photosQty)
                .collect(Collectors.toMap(PhotoDTO::getId, photoDTO -> photoDTO));
        Map<Long, PhotoDTO> photosByEarthDateSameAsPhotosBySol = marsPhotosByEarthDate.getPhotos()
                .stream()
                .filter(photoDTO -> comparedPhotosBySol.get(photoDTO.getId()) != null)
                .collect(Collectors.toMap(PhotoDTO::getId, photoDTO -> photoDTO));

        //then
        assertThat(photosByEarthDateSameAsPhotosBySol.keySet()).
                as(forNoMatch("Qty of photos by sol and earth date")).
                hasSize(comparedPhotosBySol.keySet().size());
        assertThat(photosByEarthDateSameAsPhotosBySol).
                as(forNoMatch("Photos properties by sol and earth date")).
                isEqualTo(comparedPhotosBySol);
    }

    @DataProvider
    public Object[][] photosByCuriosityCameras() {
        return new Object[][]{{10, 1000}};
    }

    @Test(description = "Qty of Mars photos made by 'Curiosity' rover cameras is within allowed range",
            dataProvider = "photosByCuriosityCameras")
    public void verifyMarsPhotosQtyByCuriosityCamerasIsWithinAllowedRange(final int allowedRange, final long sol) {
        //given
        Map<String, Integer> photosByCameras = new HashMap<>();
        curiosityRover.getCameras().forEach(camera -> {
            MarsPhotosParams photoParams = new MarsPhotosParams()
                    .withSol(sol)
                    .withCamera(camera);
            MarsPhotosDTO marsPhotos = marsRoverPhotosSteps.getMarsPhotos(curiosityRover, photoParams);
            photosByCameras.put(camera, marsPhotos.getPhotos().size());
        });
        LOGGER.info("Photos by 'Curiosity' cameras: {}", photosByCameras);

        //when
        Map<String, Integer> copyPhotosByCameras = new HashMap<>(photosByCameras);
        Map<String, List<String>> errorCameras = new HashMap<>();
        photosByCameras.forEach((currentKey, currentValue) -> {
            copyPhotosByCameras.remove(currentKey);
            copyPhotosByCameras.forEach((nextKey, nextValue) -> {
                if ((allowedRange * (currentValue != 0 ? currentValue : 1)) <= nextValue) {
                    updateMapValues(errorCameras, nextKey, currentKey);
                }
                if (allowedRange * (nextValue != 0 ? nextValue : 1) <= currentValue) {
                    updateMapValues(errorCameras, currentKey, nextKey);
                }
            });
        });

        //then
        assertThat(errorCameras).as("Cameras have %d times more images than a list of cameras in []", allowedRange).
                isEmpty();
    }

    private void updateMapValues(final Map<String, List<String>> map, final String key, final String newValue) {
        List<String> values = map.containsKey(key) ? map.get(key) : new ArrayList<>();
        values.add(newValue);
        map.put(key, values);
    }
}