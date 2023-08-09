package com.deben.weather.service;

import com.deben.weather.model.Weather;

import java.io.IOException;

public interface PDFGenerateService {
    byte[] generateReport(Weather weather) throws IOException;
}
