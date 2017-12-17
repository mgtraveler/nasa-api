package gov.nasa.api.enums;

import java.util.Arrays;
import java.util.List;

import static gov.nasa.api.enums.Rover.Camera.*;

public enum Rover {
    CURIOSITY(Arrays.asList(PHAZ, RHAZ, MAST, CHEMCAM, MAHLI, MARDI, NAVCAM));

    private List<String> cameras;

    Rover(final List<String> cameras) {
        this.cameras = cameras;
    }

    public List<String> getCameras() {
        return cameras;
    }

    protected static class Camera {
        static final String PHAZ = "phaz";
        static final String RHAZ = "rhaz";
        static final String MAST = "mast";
        static final String CHEMCAM = "chemcam";
        static final String MAHLI = "mahli";
        static final String MARDI = "mardi";
        static final String NAVCAM = "navcam";
    }
}