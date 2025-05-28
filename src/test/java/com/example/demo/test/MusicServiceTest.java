package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.MusicDTO;
import com.example.demo.service.MusicService;

@SpringBootTest
@TestPropertySource(properties = {
    "spotify.client.id=5599bee3670b4ce78e20765160bfe3ea",
    "spotify.client.secert=527b41bc7ddf4aa3a3d4adad39fc5de0"
})
public class MusicServiceTest {

    @Autowired
    MusicService service;
    WebClient webClient;

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

    	        List<MusicDTO> result = service.getPlaylistTracks(playlistId);

    	        assertNotNull(result);
    	        assertFalse(result.isEmpty());

    	        System.out.println("✅ Playlist Tracks:");
    	        for (MusicDTO track : result) {
    	            System.out.println(track);
    	        }
    	    }

    
    @Test
	public void 트랙선택() throws Exception {
	        
	        MusicDTO result = service.getTrackFromPlaylist("0lbdVv8GxIpJehtEca2FFu", 3);  // 두 번째 트랙
	        System.out.println("Track Info: " + result);

	}
    
    @Test
    public void 음악검색() throws Exception {
        String result = service.search("Beatles", "track", "US", 5, 0, false);
        assertNotNull(result);
        assertTrue(result.contains("tracks"));
        System.out.println(result);
    }
    
    
    
}

