package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.service.MusicService;

@SpringBootTest
@TestPropertySource(properties = {
    "spotify.client.id=test-client-id",
    "spotify.client.secret=test-client-secret"
})
public class MusicServiceTest {

    @Autowired
    MusicService service;

    @Test
    public void 토큰발급() throws Exception {
        String token = service.getAccessToken();
        assertNotNull(token);
        assertFalse(token.isBlank());
        System.out.println("Access Token: " + token);
    }
}
