package com.deben.weather.controller;

import com.deben.weather.model.Weather;
import com.deben.weather.service.PDFGenerateService;
import com.deben.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private PDFGenerateService pdfGenerateService;

    @Test
    public void testGetWeather() throws Exception {
        String city = "testCity";
        Weather mockWeather = new Weather(); // you should fill this with some test data

        when(weatherService.getWeatherDataCity(city)).thenReturn(mockWeather);

        mockMvc.perform(get("/api/weather/{city}", city))
                .andExpect(status().isOk());
        // You can add more expectations here to test the response body
    }

    @Test
    public void testDownloadFile() throws Exception {
        String city = "testCity";
        byte[] mockPdfBytes = new byte[0]; // you should fill this with some test data

        when(pdfGenerateService.generateReport(any(Weather.class))).thenReturn(mockPdfBytes);

        mockMvc.perform(get("/api/report/{city}", city))
                .andExpect(status().isOk());
        // You can add more expectations here to test the response body and headers
    }

}