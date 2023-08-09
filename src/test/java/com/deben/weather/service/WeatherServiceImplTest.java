package com.deben.weather.service;

import com.deben.weather.dao.WeatherDAO;
import com.deben.weather.model.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherServiceImplTest {
    private WeatherDAO weatherDAOMock;
    private WeatherServiceImpl weatherService;

    @BeforeEach
    public void setUp() {
        weatherDAOMock = Mockito.mock(WeatherDAO.class);
        weatherService = new WeatherServiceImpl(weatherDAOMock);
    }


    @Test
    public void testGetWeatherDataCity() throws IOException {
        String city = "Cairo";
        String mockedJson = new String(WeatherServiceImplTest.class.getResourceAsStream("/sampleResponse.json").readAllBytes(), StandardCharsets.UTF_8);

        Weather mockedWeather = new Weather(); // replace with a mocked Weather object

        //Setting the Weather object
        mockedWeather.setCity("Cairo");
        mockedWeather.setCountry("Egypt");
        mockedWeather.setCountryISOCode("EG");
        mockedWeather.setHumidity(61.0);
        mockedWeather.setPressure(1011.0);
        mockedWeather.setTemperature(84.96);
        mockedWeather.setTempFeelsLike(89.49);
        mockedWeather.setTempMax(86.41);
        mockedWeather.setTempMin(84.96);
        mockedWeather.setTimeZone(10800);
        mockedWeather.setWeather("Clear");
        mockedWeather.setWeatherDesc("clear sky");

        when(weatherDAOMock.getWeatherDataCity(city)).thenReturn(mockedJson);

        Weather result = weatherService.getWeatherDataCity(city);


        assertEquals(mockedWeather, result);
    }
}