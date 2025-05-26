package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.config.SpotifyConfig;
import com.example.demo.service.MusicService;

@SpringBootTest
@TestPropertySource(properties = {
	    "spotify.client-id=test-client-id",
	    "spotify.client-secret=test-client-secret"
	})
public class MusicServiceTest2 {
	
	@Autowired
	MusicService service;
	SpotifyConfig config;

	@Test
	public void 토큰발급() throws Exception {

		ReflectionTestUtils.setField(service, "clientId", "your-client-id");
		ReflectionTestUtils.setField(service, "clientSecret", "your-client-secret");

		// when
		String token = service.getAccessToken();

		// then
		assertNotNull(token);
		assertFalse(token.isBlank());
		System.out.println("Access Token: " + token);

	}
}
