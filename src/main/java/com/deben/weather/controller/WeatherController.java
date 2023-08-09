package com.deben.weather.controller;


import com.deben.weather.model.Weather;
import com.deben.weather.service.PDFGenerateService;
import com.deben.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @Autowired
    PDFGenerateService pdfGenerateService;

    @GetMapping("/api/weather/{city}")
    public Weather getWeather(@PathVariable String city) throws IOException {
        return weatherService.getWeatherDataCity(city);
    }


    @GetMapping("/api/report/{city}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String city) throws IOException {
        //  String mockedJson = new String(WeatherController.class.getResourceAsStream("/sampleResponse.json").readAllBytes(), StandardCharsets.UTF_8);
        Weather weather = weatherService.getWeatherDataCity(city);
        byte[] pdfBytes = pdfGenerateService.generateReport(weather);
        // Save the PDF document.
        String fileName = String.format("Weather_%s.pdf", city);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(pdfBytes);

    }


}