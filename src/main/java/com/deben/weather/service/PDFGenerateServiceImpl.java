package com.deben.weather.service;

import com.deben.weather.model.Weather;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFGenerateServiceImpl implements PDFGenerateService {

    @Override
    public byte[] generateReport(Weather weather) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD_OBLIQUE, 12);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Weather Report for " + weather.getCity());
                contentStream.newLineAtOffset(0, -50);
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.showText("Weather: " + weather.getWeatherDesc());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Current Temperature: " + weather.getTemperature());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Min Temperature: " + weather.getTempMin());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Max Temperature: " + weather.getTempMax());
                contentStream.endText();

            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }

}
