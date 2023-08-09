package com.deben.weather.service;

import com.deben.weather.model.Weather;
import jakarta.validation.constraints.AssertTrue;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PDFGenerateServiceImplTest {

    @InjectMocks
    PDFGenerateServiceImpl pdfGenerateService;

    @Test
    public void testGenerateReport() throws IOException {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a mock Weather object
        Weather weather = mock(Weather.class);
        when(weather.getCity()).thenReturn("Cairo");
        when(weather.getWeatherDesc()).thenReturn("Sunny");
        when(weather.getTemperature()).thenReturn(25.0);
        when(weather.getTempMin()).thenReturn(23.0);
        when(weather.getTempMax()).thenReturn(27.0);

        // Call the method under test
        byte[] result = pdfGenerateService.generateReport(weather);
        assertNotNull(result);
        assertTrue(result.length > 0);

        // Load the PDF document
        InputStream targetStream = new ByteArrayInputStream(result);
        PDDocument document = PDDocument.load(targetStream);


        // Create a PDFTextStripper
        PDFTextStripper pdfStripper = new PDFTextStripper();

        // Extract the text from the PDF
        String text = pdfStripper.getText(document);

        // Close the document
        document.close();

        // Check if the text is present in the PDF
        assertTrue(text.contains( "Weather Report for " + weather.getCity()));
        assertTrue(text.contains( "Weather: " + weather.getWeatherDesc()));
        assertTrue(text.contains( "Current Temperature: " + weather.getTemperature()));
        assertTrue(text.contains( "Min Temperature: " + weather.getTempMin()));
        assertTrue(text.contains( "Max Temperature: " + weather.getTempMax()));


    }
}