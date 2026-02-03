package com.example.rest_api_assignment.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class DummyJwtFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        if(req.getCookies() != null)
        {
            for(Cookie cookie: req.getCookies())
            {
                if(cookie.getName().equals("AUTH_TOKEN") && cookie.getValue().equals("dummy-cookie-token")){
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            "dummyUser",
                            null,
                            List.of(() -> "ROLE1")
                    );
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }
        filterChain.doFilter(req, res);
    }
}
