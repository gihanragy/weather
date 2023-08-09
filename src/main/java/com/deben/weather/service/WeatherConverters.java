package com.deben.weather.service;


import com.deben.weather.model.Weather;
import org.json.JSONObject;

import java.util.function.Function;

public class WeatherConverters {
    public static final Function<String, Weather> weatherConverter
            = json -> {
        JSONObject obj = new JSONObject(json);

        String name = obj.getString("name");
        String country = obj.getJSONObject("sys").getString("country");
        double humidity = obj.getJSONObject("main").getInt("humidity");
        double pressure = obj.getJSONObject("main").getInt("pressure");
        double temperature = obj.getJSONObject("main").getDouble("temp");
        double tempFeelsLike = obj.getJSONObject("main").getDouble("feels_like");
        double tempMax = obj.getJSONObject("main").getDouble("temp_max");
        double tempMin = obj.getJSONObject("main").getDouble("temp_min");
        double timeZone = obj.getDouble("timezone");
        String weatherMain = obj.getJSONArray("weather").getJSONObject(0).getString("main");
        String weatherDesc = obj.getJSONArray("weather").getJSONObject(0).getString("description");

        //Creating the Weather object
        Weather weather = new Weather();

        //Setting the Weather object
        weather.setCity(name);
        weather.setCountry(new CountryCodes().getCountry(country));
        weather.setCountryISOCode(country);
        weather.setHumidity(humidity);
        weather.setPressure(pressure);
        weather.setTemperature(temperature);
        weather.setTempFeelsLike(tempFeelsLike);
        weather.setTempMax(tempMax);
        weather.setTempMin(tempMin);
        weather.setTimeZone(timeZone);
        weather.setWeather(weatherMain);
        weather.setWeatherDesc(weatherDesc);
        return weather;
    };


}
