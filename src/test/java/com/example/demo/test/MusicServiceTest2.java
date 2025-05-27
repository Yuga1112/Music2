package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.config.SpotifyConfig;
import com.example.demo.service.MusicService;

@SpringBootTest
@TestPropertySource(properties = {
	    "spotify.client.id=test-client-id",
	    "spotify.client.secret=test-client-secret"
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
	
	@Test
	public void 재생목록테스트() throws Exception {
		
		 String playlistId = "0lbdVv8GxIpJehtEca2FFu";  // 테스트할 재생목록 ID

	        String result = service.getPlaylistTracks(playlistId);

	        assertNotNull(result);
	        assertTrue(result.contains("tracks"));  // 응답 JSON 안에 tracks 필드가 있는지 확인

	        System.out.println("Playlist JSON Response:\n" + result);
	        
	       // https://open.spotify.com/playlist/0lbdVv8GxIpJehtEca2FFu?si=ZA-QrZcaSa6k15LUktf8KQ     
	}
	
	@Test
	public void 트랙선택() throws Exception {
		
		 String playlistId = "0lbdVv8GxIpJehtEca2FFu";

	        String firstTrack = service.getFirstTrackFromPlaylist(playlistId);

	        assertNotNull(firstTrack);
	        System.out.println("첫 번째 트랙 정보:\n" + firstTrack);
		
	}
	}
