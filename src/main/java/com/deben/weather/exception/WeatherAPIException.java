package com.deben.weather.exception;

import org.springframework.http.HttpStatus;

public class WeatherAPIException extends ServiceException {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1;

    public WeatherAPIException(String code, String message) {
        super(code, message);
        status = HttpStatus.BAD_REQUEST;

    }


}
