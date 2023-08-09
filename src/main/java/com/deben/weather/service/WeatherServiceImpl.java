package com.deben.weather.service;

import com.deben.weather.dao.WeatherDAO;
import com.deben.weather.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class WeatherServiceImpl implements WeatherService {

    private WeatherDAO wDAO;

    @Autowired
    public WeatherServiceImpl(WeatherDAO weatherDAO) {
        this.wDAO = weatherDAO;
    }

    @Override
    public Weather getWeatherDataCity(String city)  {
        return jsonParseCityWeather(city);
    }

    //Retrieves weather data in JSON format and assigns it to a String variable.
    private Weather jsonParseCityWeather(String city) {
        String json = this.wDAO.getWeatherDataCity(city);
        return WeatherConverters.weatherConverter.apply(json);
    }
}