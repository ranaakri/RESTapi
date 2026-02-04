package com.example.rest_api_assignment.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    //Adds Http Only Cookie
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("AUTH_TOKEN", "dummy-cookie-token");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        return ResponseEntity.ok("Login Success");
    }
}
