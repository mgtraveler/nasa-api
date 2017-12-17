package gov.nasa.api.services;

import gov.nasa.api.services.photos.MarsRoverPhotosClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static gov.nasa.api.core.props.Props.CONFIG;

public class ServiceProvider {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(CONFIG.host())
            .addConverterFactory(JacksonConverterFactory.create())
            .client(getOkHttpClient())
            .build();


    public static MarsRoverPhotosClient getMarsRoverPhotosClient() {
        return retrofit.create(MarsRoverPhotosClient.class);
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.networkInterceptors().add(httpLoggingInterceptor);
        return builder.build();
    }
}