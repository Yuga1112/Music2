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


//@Component
//@ConfigurationProperties(prefix = "spotify")
//public class SpotifyConfig {
//
//    private String clientId;
//    private String clientSecret;
//
//    // Getter & Setter
//    public String getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(String clientId) {
//        this.clientId = clientId;
//    }
//
//    public String getClientSecret() {
//        return clientSecret;
//    }
//
//    public void setClientSecret(String clientSecret) {
//        this.clientSecret = clientSecret;
//    }
//}
