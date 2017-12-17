package gov.nasa.api.photos;

import com.fasterxml.jackson.databind.JsonNode;
import gov.nasa.api.BaseTest;
import gov.nasa.api.core.annotations.TestData;
import gov.nasa.api.enums.Rover;
import gov.nasa.api.services.photos.MarsPhotosParams;
import gov.nasa.api.services.photos.response.MarsPhotosDTO;
import gov.nasa.api.services.photos.response.PhotoDTO;
import gov.nasa.api.steps.MarsRoverPhotosSteps;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static gov.nasa.api.core.common.ErrorMsg.forNoMatch;
import static gov.nasa.api.core.mapper.JsonMapper.readToObject;
import static gov.nasa.api.core.logger.Logger.LOGGER;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverPhotosTest extends BaseTest{

    private static final Rover curiosityRover = Rover.CURIOSITY;

    private MarsRoverPhotosSteps marsRoverPhotosSteps = new MarsRoverPhotosSteps();

    @Test(description = "Mars photos made by 'Curiosity' rover are equal based on sol and earth date",
            dataProvider = DATA_PROVIDER_METHOD)
    @TestData("photos/verifyMarsCuriosityPhotosBySolAndEarthDateAreEqual.json")
    public void verifyMarsCuriosityPhotosBySolAndEarthDateAreEqual(final JsonNode jsonNode) {
        //given
        int photosQty = jsonNode.get("photosQty").asInt();
        MarsPhotosParams paramsBySol = readToObject(jsonNode.get("paramsBySol"), MarsPhotosParams.class);
        MarsPhotosParams paramsByEarthDate =  readToObject(jsonNode.get("paramsByEarthDate"), MarsPhotosParams.class);

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

    @Test(description = "Qty of Mars photos made by 'Curiosity' rover cameras is within allowed range",
            dataProvider = DATA_PROVIDER_METHOD)
    @TestData("photos/verifyMarsPhotosQtyByCuriosityCamerasIsWithinAllowedRange.json")
    public void verifyMarsPhotosQtyByCuriosityCamerasIsWithinAllowedRange(final JsonNode jsonNode) {
        //given
        int allowedRange = jsonNode.get("allowedRange").asInt();
        MarsPhotosParams paramsBySol = readToObject(jsonNode.get("paramsBySol"), MarsPhotosParams.class);

        Map<String, Integer> photosByCameras = new HashMap<>();
        curiosityRover.getCameras().forEach(camera -> {
            paramsBySol.setCamera(camera);
            MarsPhotosDTO marsPhotos = marsRoverPhotosSteps.getMarsPhotos(curiosityRover, paramsBySol);
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
        assertThat(errorCameras).
                as("Cameras have %d times more images than a list of cameras in []", allowedRange).isEmpty();
    }

    private void updateMapValues(final Map<String, List<String>> map, final String key, final String newValue) {
        List<String> values = map.containsKey(key) ? map.get(key) : new ArrayList<>();
        values.add(newValue);
        map.put(key, values);
    }
}