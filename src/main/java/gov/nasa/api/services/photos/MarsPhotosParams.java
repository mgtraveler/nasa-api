package gov.nasa.api.services.photos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static gov.nasa.api.core.props.Props.CONFIG;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarsPhotosParams {

    private Long sol;
    private String earthDate;
    private String camera;
    private String apiKey;
    private Integer page;

    public Long getSol() {
        return sol;
    }

    public void setSol(final long sol) {
        this.sol = Long.valueOf(sol);
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(final String earthDate) {
        this.earthDate = earthDate;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(final String camera) {
        this.camera = camera;
    }

    public String getApiKey() {
        return apiKey != null ? apiKey : CONFIG.apiKey();
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = Integer.valueOf(page);
    }
}