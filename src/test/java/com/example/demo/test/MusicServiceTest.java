package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.service.MusicService;

@SpringBootTest
@TestPropertySource(properties = {
    "spotify.client.id=5599bee3670b4ce78e20765160bfe3ea",
    "spotify.client.527b41bc7ddf4aa3a3d4adad39fc5de0"
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
    
    @Test
    public void 재생목록테스트() throws Exception { 
    	 
    	    String playlistId = "0lbdVv8GxIpJehtEca2FFu";

    	    String result = service.getPlaylistTracks(playlistId);

    	    assertNotNull(result);
    	    assertTrue(result.contains("tracks"));

    	    System.out.println("✅ Playlist JSON Response:\n" + result);
    
    }
    
    @Test
	public void 트랙선택() throws Exception {
		
		 String playlistId = "0lbdVv8GxIpJehtEca2FFu";

	        String firstTrack = service.getFirstTrackFromPlaylist(playlistId);

	        assertNotNull(firstTrack);
	        System.out.println("첫 번째 트랙 정보:\n" + firstTrack);
		
	}
    
}

