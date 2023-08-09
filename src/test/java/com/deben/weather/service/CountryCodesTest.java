package com.deben.weather.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryCodesTest {

    @Test
    public void testGetCountry() {
        String countryCode = "EG"; // replace with a known country code
        String expectedCountry = "Egypt"; // replace with the expected country name

        CountryCodes countryCodes = new CountryCodes();

        String result = countryCodes.getCountry(countryCode);

        assertEquals(expectedCountry, result);
    }

    @Test
    public void testGetCountryWithInvalidCode() {
        String countryCode = "XX"; // replace with an invalid country code
        String expectedCountry = ""; // replace with the expected country name for invalid code

        CountryCodes countryCodes = new CountryCodes();

        String result = countryCodes.getCountry(countryCode);

        assertEquals(expectedCountry, result);
    }
}