package com.deben.weather.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    private static final String ERRORS_PREFIX = "errors.";
    private  MessageSource messageSource;

    @Autowired
    public ExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<?> handleGeneralException(Exception exception) {
        log.warn("Exception of instance class {} message is {}", exception.getClass(), exception.getMessage());
        ServiceResponse serviceResponse = ServiceResponse.builder()
                .details(exception.getMessage())
                .code(AppConstants.GENERAL_ERROR_CODE)
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = {WeatherAPIException.class})
    public ResponseEntity<?> handleException(WeatherAPIException exception) {
        log.warn("Exception of instance class HttpClientErrorException status code {} message is {}", exception.getCode(), exception.getDetails());
        String code;

        switch (exception.getCode()) {
            case "404":
                code = AppConstants.GENERAL_ERROR_CODE_NOT_FOUND;
                break;
            case "400":
                code = AppConstants.GENERAL_ERROR_CODE_BAD_REQUEST;
                break;

            case "401":
                code = AppConstants.GENERAL_ERROR_CODE_NOT_AUTHORIZED;
                break;
            case "403":
                code = AppConstants.GENERAL_ERROR_CODE_FORBIDEN;
                break;
            case "500":
                code = AppConstants.GENERAL_SERVER_CODE;
                break;
            default:
                code = AppConstants.GENERAL_ERROR_CODE;
                break;
        }
        String localizedMessage = (exception.getDetails()==null || exception.getDetails().isBlank()) ? resolveLocalizedErrorMessage(code):exception.getDetails();
        ServiceResponse serviceResponse = ServiceResponse.builder()
                .details(localizedMessage)
                .code(code)
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.valueOf(Integer.parseInt(exception.getCode())));
    }




    public String resolveLocalizedErrorMessage(String code, Object... args) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String key = ERRORS_PREFIX + code;
        String localizedMessage = messageSource.getMessage(key, null, "", currentLocale);
        return String.format(localizedMessage != null ? localizedMessage : "", args);
    }
}
