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

    //Filtering Method
    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        //If any cookies present
        if(req.getCookies() != null)
        {
            //Checks all cookies
            for(Cookie cookie: req.getCookies())
            {
                //Compares with the token
                if(cookie.getName().equals("AUTH_TOKEN") && cookie.getValue().equals("dummy-cookie-token")){
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            "dummyUser",
                            null,
                            List.of(() -> "ROLE1")
                    );

                    //Adds info into Security context of spring boot
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }
        //Pass request to next
        filterChain.doFilter(req, res);
    }
}
