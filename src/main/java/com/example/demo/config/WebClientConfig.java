package com.example.demo.config;

import java.net.http.HttpRequest.Builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.spotify.com")  // 기본 URL 설정
                .build();
    }

}


