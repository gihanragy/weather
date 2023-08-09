package com.deben.weather.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionHandlerTest {

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";
    public static final String GENERAL_EXCEPTION_MESSAGE = "General Exception";
    public static final String BAD_EXCEPTION_MESSAGE = "Bad Exception";
    public static final String NOT_AUTHORIZED_MESSGE = "Not Authorized";
    public static final String FORBIDIN_MESSAGE = "Forbidin";
    public static final String NOT_FOUND_MESSAGE = "Not FOUND";
    @InjectMocks
    private ExceptionHandler exceptionHandler;

    @Mock
    private MessageSource messageSource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleGeneralException() {
        Exception exception = new Exception("Test Exception");
        ResponseEntity<?> response = exceptionHandler.handleGeneralException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testHandleWeatherAPIException() {
        WeatherAPIException badException = new WeatherAPIException("400", BAD_EXCEPTION_MESSAGE);
            WeatherAPIException  notAuthorizedException = new WeatherAPIException("401", NOT_AUTHORIZED_MESSGE);
            WeatherAPIException forrbidinxception = new WeatherAPIException("403", FORBIDIN_MESSAGE);
            WeatherAPIException notFoundException = new WeatherAPIException("404", NOT_FOUND_MESSAGE);
            WeatherAPIException internalServerError = new WeatherAPIException("500", "");
            WeatherAPIException otherException = new WeatherAPIException("409","");
            when(messageSource.getMessage(anyString(), any(), anyString(), any(Locale.class)))
                    .thenReturn(GENERAL_EXCEPTION_MESSAGE);
        assertResponse(badException,HttpStatus.BAD_REQUEST,AppConstants.GENERAL_ERROR_CODE_BAD_REQUEST,BAD_EXCEPTION_MESSAGE);
        assertResponse(notAuthorizedException,HttpStatus.UNAUTHORIZED,AppConstants.GENERAL_ERROR_CODE_NOT_AUTHORIZED,NOT_AUTHORIZED_MESSGE);
        assertResponse(forrbidinxception,HttpStatus.FORBIDDEN,AppConstants.GENERAL_ERROR_CODE_FORBIDEN,FORBIDIN_MESSAGE);
        assertResponse(notFoundException,HttpStatus.NOT_FOUND,AppConstants.GENERAL_ERROR_CODE_NOT_FOUND,NOT_FOUND_MESSAGE);
        assertResponse(internalServerError,HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.GENERAL_SERVER_CODE,GENERAL_EXCEPTION_MESSAGE);
        assertResponse(otherException,HttpStatus.valueOf(409),AppConstants.GENERAL_ERROR_CODE,GENERAL_EXCEPTION_MESSAGE);



    }
    private void assertResponse(WeatherAPIException exception,HttpStatus status,String exceptedCode,String expectedMessage)
    {
        ResponseEntity<?> response = exceptionHandler.handleException(exception);
        assertEquals(status, response.getStatusCode());
        ServiceResponse serviceResponse = (ServiceResponse) response.getBody();
        assertEquals(exceptedCode,serviceResponse.getCode());
        assertEquals(expectedMessage,serviceResponse.getDetails());
    }


}