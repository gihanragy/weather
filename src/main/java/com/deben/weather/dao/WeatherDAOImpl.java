package com.deben.weather.dao;

import com.deben.weather.exception.AppConstants;
import com.deben.weather.exception.WeatherAPIException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class WeatherDAOImpl implements WeatherDAO {


    private final String rapidApiKey;

    private final String rapidapiComCity;
    private final String rapidapiHost;
    private final OkHttpClient client;

    @Autowired
    public WeatherDAOImpl(OkHttpClient client, @Value("${weather.api.key}") String rapidApiKey, @Value("${weather.url}") String rapidapiComCity, @Value("${weather.host.url}") String rapidapiHost) {

        this.client = client;
        this.rapidApiKey = rapidApiKey;
        this.rapidapiComCity = rapidapiComCity;
        this.rapidapiHost = rapidapiHost;

    }

    //Get current weather for any given city.
    @Override
    public String getWeatherDataCity(String city) {

        return connectAPICity(city);

    }


    //Gets weather data for current time
    private String connectAPICity(String city) {

        String url = String.format(rapidapiComCity, city);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", rapidApiKey)
                .addHeader("X-RapidAPI-Host", rapidapiHost)
                .build();
        return getResponse(client, request);

    }


    private String getResponse(OkHttpClient client, Request request) {

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) { // Checks for 200 OK status
                return response.body().string();
            } else {
                throw new WeatherAPIException(response.code() + "", "");
            }
        } catch (IOException e) {
            throw new WeatherAPIException(AppConstants.GENERAL_ERROR_CODE, e.getMessage());
        }

    }

}