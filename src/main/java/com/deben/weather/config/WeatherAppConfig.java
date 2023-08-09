package com.deben.weather.config;

import okhttp3.OkHttpClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WeatherAppConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource =
                new ResourceBundleMessageSource();

        messageSource.setBasename("translation/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheMillis(1000);
        return messageSource;
    }

}