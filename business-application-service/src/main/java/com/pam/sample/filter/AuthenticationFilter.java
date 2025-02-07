package com.pam.sample.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends AnonymousAuthenticationFilter {

    public AuthenticationFilter() {
        super("PROXY_AUTH_FILTER");
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext()
                .setAuthentication(createAuthentication((HttpServletRequest) req));
        log.debug("SecurityContextHolder pre-auth user: {}", SecurityContextHolder.getContext());
        log.debug("Populated SecurityContextHolder with authenticated user: {}",
                SecurityContextHolder.getContext().getAuthentication());
        chain.doFilter(req, res);
    }

    @Override
    protected Authentication createAuthentication(final HttpServletRequest request)
            throws AuthenticationException {
        final List<? extends GrantedAuthority> authorities =
                Collections.unmodifiableList(Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        return new AnonymousAuthenticationToken("ANONYMOUS", "ServiceGroup", authorities);
    }
}