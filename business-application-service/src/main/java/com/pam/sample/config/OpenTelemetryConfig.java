package com.pam.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.contrib.sampler.RuleBasedRoutingSampler;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import io.opentelemetry.semconv.UrlAttributes;

@Configuration(proxyBeanMethods = false)
public class OpenTelemetryConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${management.endpoints.web.base-path:/actuator}")
    private String actuatorBasePath;

    @Bean
    Tracer tracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer(this.applicationName);
    }

    @Bean
    AutoConfigurationCustomizerProvider otelCustomizer() {
        String pattern = "^" + this.actuatorBasePath;
        return p -> p.addSamplerCustomizer((fallback, config) -> RuleBasedRoutingSampler
                .builder(SpanKind.SERVER, fallback).drop(UrlAttributes.URL_PATH, pattern).build());
    }
}
