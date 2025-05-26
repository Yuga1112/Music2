package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyConfig {
    
    @Value("${spotify.client.id}")
    public String clientId;

    @Value("${spotify.client.secret}")
    public String clientSecret;
}
