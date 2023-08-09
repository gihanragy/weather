package com.deben.weather.service;

import com.deben.weather.model.Weather;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherConvertersTest {

    @Test
    public void testWeatherConverter() throws IOException {
        String mockedJson = new String(WeatherServiceImplTest.class.getResourceAsStream("/sampleResponse.json").readAllBytes(), StandardCharsets.UTF_8);
        Weather expectedWeather = new Weather(); // replace with the expected Weather object
        expectedWeather.setCity("Cairo");
        expectedWeather.setCountry("Egypt");
        expectedWeather.setCountryISOCode("EG");
        expectedWeather.setHumidity(61.0);
        expectedWeather.setPressure(1011.0);
        expectedWeather.setTemperature(84.96);
        expectedWeather.setTempFeelsLike(89.49);
        expectedWeather.setTempMax(86.41);
        expectedWeather.setTempMin(84.96);
        expectedWeather.setTimeZone(10800);
        expectedWeather.setWeather("Clear");
        expectedWeather.setWeatherDesc("clear sky");
        Weather actual = WeatherConverters.weatherConverter.apply(mockedJson);

        assertEquals(expectedWeather, actual);
    }
}