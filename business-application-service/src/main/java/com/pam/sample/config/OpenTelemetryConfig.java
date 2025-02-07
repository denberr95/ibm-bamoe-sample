package com.pam.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;

@Configuration(proxyBeanMethods = false)
public class OpenTelemetryConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    Tracer tracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer(this.applicationName);
    }
}
