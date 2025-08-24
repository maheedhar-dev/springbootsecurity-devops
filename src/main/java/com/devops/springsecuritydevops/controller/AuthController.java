package com.devops.springsecuritydevops.controller;

import com.devops.springsecuritydevops.model.AuthRequest;
import com.devops.springsecuritydevops.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

private final AuthenticationManager authenticationManager;
private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }

    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        // Get user details
        UserDetails user = (UserDetails) auth.getPrincipal();

        // Generate JWT
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(token);
    }
}
