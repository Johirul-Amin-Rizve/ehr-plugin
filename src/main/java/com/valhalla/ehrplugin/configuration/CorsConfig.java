package com.valhalla.ehrplugin.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfig {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String[] allowedMethods;

    @Value("${cors.allowed-headers}")
    private String[] allowedHeaders;

    @Value("${cors.allow-credentials}")
    private boolean allowCredentials;

    @Value("${cors.max-age}")
    private long maxAge;

    // Getter methods
    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }

    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    public String[] getAllowedHeaders() {
        return allowedHeaders;
    }

    public boolean isAllowCredentials() {
        return allowCredentials;
    }

    public long getMaxAge() {
        return maxAge;
    }
}

