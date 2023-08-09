package com.deben.weather.dao;

import com.deben.weather.exception.WeatherAPIException;
import com.deben.weather.service.WeatherServiceImplTest;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherDAOImplTest {

    @Mock
    private OkHttpClient clientMock;

    private WeatherDAOImpl weatherDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherDAO = new WeatherDAOImpl(clientMock,"7f2911b5a6mshe09735b5272875dp1cba6djsn2bce4cd08320","https://open-weather13.p.rapidapi.com/city/%s","open-weather13.p.rapidapi.com");
    }

    @Test
    public void testGetWeatherDataCity_success() throws IOException {
        // Given
        String city = "Cairo";
        String expectedResponse = new String(WeatherServiceImplTest.class.getResourceAsStream("/sampleResponse.json").readAllBytes(), StandardCharsets.UTF_8);


        Call callMock = mock(Call.class);
        Response responseMock = mock(Response.class);
        ResponseBody responseBodyMock = mock(ResponseBody.class);

        when(clientMock.newCall(any(Request.class))).thenReturn(callMock);
        when(callMock.execute()).thenReturn(responseMock);
        when(responseMock.body()).thenReturn(responseBodyMock);
        when(responseMock.isSuccessful()).thenReturn(true);
        when(responseBodyMock.string()).thenReturn(expectedResponse);
        // When
        String actualResponse = weatherDAO.getWeatherDataCity(city);
        // Then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetWeatherDataCity_failure() throws IOException {
        String city = "Test City";
        Call callMock = mock(Call.class);
        Response responseMock = mock(Response.class);

        when(clientMock.newCall(any(Request.class))).thenReturn(callMock);
        when(callMock.execute()).thenReturn(responseMock);
        when(responseMock.isSuccessful()).thenReturn(false);
        when(responseMock.code()).thenReturn(400);
        when(responseMock.message()).thenReturn("Bad Request");

        assertThrows(WeatherAPIException.class, () -> weatherDAO.getWeatherDataCity(city));
    }

    @Test
    public void testGetWeatherDataCity_ioException() throws IOException {
        String city = "Test City";
        Call callMock = mock(Call.class);
        when(clientMock.newCall(any(Request.class))).thenReturn(callMock);
        when(callMock.execute()).thenThrow(new IOException("Test IOException"));

        assertThrows(WeatherAPIException.class, () -> weatherDAO.getWeatherDataCity(city));
    }


}