package com.pam.sample.config;

import static org.springframework.security.config.Customizer.withDefaults;
import com.pam.sample.filter.AuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;

@Configuration(value = "kieServerSecurity", proxyBeanMethods = false)
public class DefaultWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.anonymous(anonymous -> anonymous.authenticationFilter(new AuthenticationFilter()))
                .cors(withDefaults())
                .csrf((Customizer<CsrfConfigurer<HttpSecurity>>) CsrfConfigurer::disable)
                .authorizeRequests(requests -> requests.antMatchers("/*").permitAll())
                .headers(headers -> headers.frameOptions().disable());
    }
}
