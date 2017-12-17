package gov.nasa.api.services.photos;

import static gov.nasa.api.core.props.Props.CONFIG;

public class MarsPhotosParams {

    private Long sol;
    private String earthDate;
    private String camera;
    private String apiKey;
    private Integer page;

    public Long getSol() {
        return sol;
    }

    public MarsPhotosParams withSol(long sol) {
        this.sol = Long.valueOf(sol);
        return this;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public MarsPhotosParams withEarthDate(String earthDate) {
        this.earthDate = earthDate;
        return this;
    }

    public String getCamera() {
        return camera;
    }

    public MarsPhotosParams withCamera(String camera) {
        this.camera = camera;
        return this;
    }

    public String getApiKey() {
        return apiKey != null ? apiKey : CONFIG.apiKey();
    }

    public MarsPhotosParams withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public MarsPhotosParams withPage(int page) {
        this.page = Integer.valueOf(page);
        return this;
    }
}