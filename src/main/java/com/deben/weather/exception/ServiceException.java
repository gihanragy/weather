package com.deben.weather.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class ServiceException extends RuntimeException {

    protected HttpStatus status;
    private String details;

    private String code;
    private Object[] arguments;

    ServiceException() {
        super();
    }

    ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.details = message;
    }

    ServiceException(String code, Object... args) {
        this.code = code;
        this.arguments = args;
    }

    ServiceException(String code, String message, Object... args) {
        super(message);
        this.code = code;
        this.arguments = args;
    }

    ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    ServiceException(Throwable cause) {
        super(cause);
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonProperty("details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("arguments")
    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object... args) {
        this.arguments = args;
    }

    @JsonProperty("status")
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }




    public static class Builder {
        private String details;
        private HttpStatus status;
        private String code;
        private Object[] arguments;


        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder arguments(Object... args) {
            this.arguments = args;
            return this;
        }

        private ServiceException init(ServiceException serviceException) {
            serviceException.setDetails(this.details);
            serviceException.setStatus(this.status);
            serviceException.setCode(this.code);
            serviceException.setArguments(this.arguments);
            return serviceException;
        }

        public ServiceException build() {
            return this.init(new ServiceException());
        }

        public ServiceException build(String message) {
            return this.init(new ServiceException(message));
        }

        public ServiceException build(String message, Throwable cause) {
            return this.init(new ServiceException(message, cause));
        }

        public ServiceException build(Throwable cause) {
            return this.init(new ServiceException(cause));
        }
    }
}
