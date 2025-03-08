package com.pam.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJms
@EnableAspectJAutoProxy
@EnableWebSecurity
@SpringBootApplication
public class BPMApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BPMApplication.class, args);
    }
}